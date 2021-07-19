package finalKadai;
import java.util.*;
public class Main {
    public static void main(String[] args){
        System.out.println("-----ゲーム開始-----\n5×5マスの海域です。\n     　　　　　     ");
        Game game = new Game();
        Scanner sc = new Scanner(System.in);
        System.out.println("難易度を選択してください\n1,簡単\n2,難しめ");
        int mode = Game.setMode(sc);
        Game.newGame(mode);
        sc.close();
    }

}