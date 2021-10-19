package rpgcreature;

import java.util.Random;

/**
 * スライムクラス
 */
public class Slime extends Monster{
    private final int ATTACK_DAMAGE=10;
    private final static String NAME="スライム";
    private final static int MAX_HP = 20;
    private final static int GOLD = 20;
    private final static int DEF = 1; 
    /**
     * スライムクラスのコンストラクタ
     */
    public Slime(){
        super(NAME,MAX_HP,GOLD,DEF);
    }

    /**
     * 攻撃メソッド
     * @param opponent：攻撃相手
     */
    @Override
    public void attack(Creature opponent) {
        
        Random r = new Random();
        int damage = r.nextInt(ATTACK_DAMAGE);
        System.out.printf("%sの攻撃！\n",getName());
        
        displayMessage(opponent,(opponent.damaged(damage)));
        
    }
    
}
