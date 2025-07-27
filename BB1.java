// sort array by specific object property
class BB {
    public char c;
    public int count = 0;
    BB(char c) {
        this.c = c;
        this.count = 0;
    }
}

public class BB1 {
    public static void main(String[] args) {
        String s = "bbccaaddddddde";
        BB[] data = new BB[26];
        for (int i = 0; i < data.length; i++) {
            data[i] = new BB((char)(i + 'a'));
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            data[c - 'a'].count++;
        }
        Arrays.sort(data, new Comparator<BB>(){
            public int compare(BB a, BB b) {
                return b.count - a.count;
            }
        });
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            if (data[i].count > 0) {
                for (int j = 0; j < data[i].count; j++) {
                    sb.append(data[i].c);
                }
            } else {
                break;
            }
        }
        System.out.print(sb.toString());
    }
}