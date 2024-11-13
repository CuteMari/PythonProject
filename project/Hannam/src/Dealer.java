public class Dealer {
    int score = 0;
    int [] pattern = new int[52];
    int pattern_cnt = 0;
    int [] num = new int[52];
    int num_cnt = 0;


    //생성자
    public Dealer(){
        System.out.println("딜러 생성");
    }

    //카드 적용 메소드
    public void Dealer_card(int pattern, int num){
        this.pattern[pattern_cnt++] = pattern;
        this.num[num_cnt++] = num;
        if(num == 1) this.score += 11;
        else if(num >= 2 && num <= 10) this.score += num;
        else this.score += 10;
    }

    public void Show_card(boolean isEnd){

        for(int i=0; i < 52; i++){
            String n = "";
            char p;
            if(this.num[i] == 0) break;


            if(this.num[i] == 1) n = "A";
            else if(this.num[i] >= 2 && this.num[i] <= 10) n = Integer.toString(this.num[i]);
            else if(this.num[i] == 11) n = "J";
            else if(this.num[i] == 12) n = "Q";
            else if(this.num[i] == 13) n = "K";


            if(this.pattern[i] == 0) p = '♠';
            else if(this.pattern[i] == 1) p = '◆';
            else if(this.pattern[i] == 2) p = '♣';
            else p = '♥';

            if(i == 1 && isEnd == false) System.out.print("XX   ");
            else System.out.print(n + "(" + p + ")   ");
        }
    }


}
