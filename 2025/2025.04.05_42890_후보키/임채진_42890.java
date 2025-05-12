import java.util.*;

class 임채진_42890 {
    public int solution(String[][] relation) {
        int answer = 0;
        int n = relation.length;
        int m = relation[0].length;
        
        List<Integer> candidateKeys = new ArrayList<>();
        
        for (int bitmask = 1; bitmask < (1 << m); bitmask++) {
            Set<String> tuples = new HashSet<>();
            
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < m; j++) {
                    if ((bitmask & (1 << j)) > 0) {
                        sb.append(relation[i][j]).append(",");
                    }
                }
                tuples.add(sb.toString());
            }
        
            if (tuples.size() == n) { // 유일성 체크
                
                boolean isMinimal = true;
                for (int key : candidateKeys) {
                    if ((key & bitmask) == key) {
                        isMinimal = false;
                        break;
                    }
                }
                
                if (isMinimal) {
                    candidateKeys.add(bitmask);
                }
                
            }
        }
        
        
        return candidateKeys.size();
    }
}
