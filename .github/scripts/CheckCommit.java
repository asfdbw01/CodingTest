import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class CheckCommit {
    public static void main(String[] args) throws Exception {
        String owner = "your-username"; // TODO: 본인 깃허브 ID
        String repo = "programmers-study"; // TODO: 본인 레포지토리 이름
        String token = System.getenv("GITHUB_TOKEN");

        LocalDate today = LocalDate.now();
        String since = today + "T00:00:00Z";

        // 1. 오늘 커밋한 사람 조회
        Set<String> todayCommitters = new HashSet<>();
        URL commitsUrl = new URL("https://api.github.com/repos/" + owner + "/" + repo + "/commits?since=" + since);
        HttpURLConnection commitsConn = (HttpURLConnection) commitsUrl.openConnection();
        commitsConn.setRequestMethod("GET");
        commitsConn.setRequestProperty("Authorization", "Bearer " + token);
        commitsConn.setRequestProperty("Accept", "application/vnd.github+json");

        BufferedReader reader = new BufferedReader(new InputStreamReader(commitsConn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        String[] commitParts = response.toString().split("\\\"login\\\":\\\"");
        for (int i = 1; i < commitParts.length; i++) {
            String username = commitParts[i].split("\\\"")[0];
            todayCommitters.add(username);
        }

        // 2. 전체 컨트리뷰터 목록 조회
        Set<String> allContributors = new HashSet<>();
        URL contributorsUrl = new URL("https://api.github.com/repos/" + owner + "/" + repo + "/contributors");
        HttpURLConnection contribConn = (HttpURLConnection) contributorsUrl.openConnection();
        contribConn.setRequestMethod("GET");
        contribConn.setRequestProperty("Authorization", "Bearer " + token);
        contribConn.setRequestProperty("Accept", "application/vnd.github+json");

        reader = new BufferedReader(new InputStreamReader(contribConn.getInputStream()));
        response = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        String[] contribParts = response.toString().split("\\\"login\\\":\\\"");
        for (int i = 1; i < contribParts.length; i++) {
            String username = contribParts[i].split("\\\"")[0];
            allContributors.add(username);
        }

        // 3. 커밋 안 한 사람 찾기
        List<String> missingUsers = new ArrayList<>();
        for (String user : allContributors) {
            if (!todayCommitters.contains(user)) {
                missingUsers.add(user);
            }
        }

        if (missingUsers.isEmpty()) {
            System.out.println("모든 멤버가 오늘 커밋했습니다.");
            return;
        }

        // 4. GitHub 이슈 생성 (POST)
        URL issueUrl = new URL("https://api.github.com/repos/" + owner + "/" + repo + "/issues");
        HttpURLConnection issueConn = (HttpURLConnection) issueUrl.openConnection();
        issueConn.setRequestMethod("POST");
        issueConn.setRequestProperty("Authorization", "Bearer " + token);
        issueConn.setRequestProperty("Accept", "application/vnd.github+json");
        issueConn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        issueConn.setDoOutput(true);

        String title = today + " 커밋 미참여자 알림";
        String body = "다음 멤버는 오늘 커밋을 하지 않았습니다:\n\n" + String.join("\n", missingUsers);
        String json = String.format("{\"title\": \"%s\", \"body\": \"%s\"}", title, body);

        OutputStream os = issueConn.getOutputStream();
        os.write(json.getBytes("utf-8"));
        os.close();

        System.out.println("[✔] 이슈가 생성되었습니다.");
    }
}
