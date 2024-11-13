import java.util.*;


public class Main {
    // 카드 (0은 제외/ 1(A) ~ 10, 11(J), 12(Q), 13(K)
    static int [][] cards = new int[4][14];
    static int winner = 0;
    static boolean isEnd = false;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random rand = new Random();


        // 게임 시작 전 초기화
        Player player = new Player();
        for(int i=0; i<2; i++){
            int [] temp = Card(cards);  // 기본 카드 나눠줌
            player.Player_card(temp[0], temp[1]);
        }
        Dealer dealer = new Dealer();
        for(int i=0; i<2; i++){
            int [] temp = Card(cards);  // 기본 카드 나눠줌
            dealer.Dealer_card(temp[0], temp[1]);
        }


        // 딜러의 점수가 17점이 넘어가면 게임 Stop
        while(true){
            // 현재 진행 상황 표시
            Show_game(player, dealer, isEnd);



            // 플레이어가 21이 넘으면 Busted
            if(player.score > 21) {
                winner = 1;
                isEnd = true;
                break;
            }

            // 플레이어에게 게임 진행 묻기
            System.out.print("Hit or Stand? (H/S): ");
            char answer = in.next().charAt(0);

            // Hit => 카드 분배
            if(answer == 'h' || answer == 'H') {
                int [] temp = Card(cards);
                player.Player_card(temp[0], temp[1]);
                temp = Card(cards);
                dealer.Dealer_card(temp[0], temp[1]);
            }
            else {  // Stand
                if(dealer.score > 21) winner = 0;
                else if(player.score < dealer.score) winner = 1;
                else if(player.score == dealer.score) winner = 2;
                else if(player.score > dealer.score) winner = 0;
                isEnd = true;
                break;
            }

            
        }

        // Winner 출력
        if (winner == 0) System.out.println("Player Wins...");
        else if(winner == 1) System.out.println("Dealer Wins...");
        else System.out.println("Drawn...");

        Show_game(player, dealer, isEnd);



    }





    //카드 분배 메소드
    public static int[] Card(int [][] cards) {
        int temp_card = -1;  // 카드 무늬 번호
        int temp_num = -1;   // 카드 번호

        Random rand = new Random();
        while(true){
            temp_card = rand.nextInt(4);  //카드 모양
            temp_num = rand.nextInt(13) + 1;  //카드 번호
            //중복 체크
            if(cards[temp_card][temp_num] != -1){
                cards[temp_card][temp_num] = -1;
                break;
            }
        }

        // 카드 무늬와 번호를 리턴
        return new int[] {temp_card, temp_num};
    }

    public static void Show_game(Player p, Dealer d, boolean isEnd){
        System.out.println("------------- Jack's BlackJack Game -------------");
        System.out.print("    # Dealer: ");
        d.Show_card(isEnd);
        System.out.print("\n    # Player: ");
        p.Show_card();
        System.out.println("\n-------------------------------------------------");
    }
}

