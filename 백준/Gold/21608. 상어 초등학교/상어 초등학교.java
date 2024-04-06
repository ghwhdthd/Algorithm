import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
public class Main {
    private static int N;
    private static int[][] site;
    private static int[] dr = {0, 0, 1, -1};
    private static int[] dc = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        site = new int[N][N];
        StringTokenizer st;
        long res = 0;
        List<Student> students = new ArrayList<>();
        Map<Integer,Student> map =new HashMap<>();
        for (int i = 0; i < N*N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int[] friends = new int[4];
            for (int j = 0; j < 4; j++) {
                friends[j] = Integer.parseInt(st.nextToken());
            }
            students.add(new Student(num, friends));
            map.put(num,new Student(num,friends));
        }

        for (Student student : students) {
            PriorityQueue<Site> cadi = new PriorityQueue<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(site[i][j] != 0) continue;
                    int aroundEmptyCount = 0;
                    int aroundLikeCount = 0;
                    //사방탐색
                    for (int k = 0; k < 4; k++) {
                        int nr = dr[k] + i;
                        int nc = dc[k] + j;
                        if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                        if(site[nr][nc] == 0) aroundEmptyCount++;
                        //좋아하는 친구 목록 중에 nr nc에 해당하는게 있으면 count++
                        for (int l = 0; l < student.likeFriends.length; l++) {
                            if(site[nr][nc] == student.likeFriends[l]) aroundLikeCount++;
                        }

                    }
                    cadi.add(new Site(i,j,aroundEmptyCount,aroundLikeCount));
                }
            }
            Site select = cadi.poll();
            site[select.r][select.c] = student.num;
        }
//        for (int i = 0; i < site.length; i++) {
//            for (int j = 0; j < site[i].length; j++) {
//                System.out.print(site[i][j]+" ");
//            }
//            System.out.println();
//        }
        for (int i = 0; i < site.length; i++) {
            for (int j = 0; j < site[i].length; j++) {
                Student student= map.get(site[i][j]);
                int aroundLikeCount = 0;
                for (int k = 0; k < 4; k++) {
                    int nr = dr[k] + i;
                    int nc = dc[k] + j;
                    if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;

                    for (int l = 0; l < 4; l++) {
                        if(student.likeFriends[l] == site[nr][nc]) aroundLikeCount++;
                    }
                }
                if(aroundLikeCount != 0){
                    res += Math.pow(10,aroundLikeCount-1);
                }
            }
        }
        System.out.println(res);

    }
    private static class Site implements Comparable<Site>{
        int r;
        int c;
        int aroundEmptySites;
        int aroundLikeSites;
        public Site(int r, int c, int aroundEmptySites, int aroundLikeSites){
            this.r = r;
            this.c = c;
            this.aroundEmptySites = aroundEmptySites;
            this.aroundLikeSites = aroundLikeSites;
        }

        @Override
        public int compareTo(Site o) {
            //비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
            //1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
            //2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.
            if(o.aroundLikeSites - this.aroundLikeSites != 0) return o.aroundLikeSites - this.aroundLikeSites;
            if(o.aroundEmptySites - this.aroundEmptySites != 0) return o.aroundEmptySites - this.aroundEmptySites;
            if(this.r - o.r != 0) return this.r - o.r;
            return this.c - o.c;
        }
    }
    private static class Student{
        int num;
        int[] likeFriends;
        public Student(int num, int[] likeFriends){
            this.num = num;
            this.likeFriends = likeFriends;
        }
    }
}