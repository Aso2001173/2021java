package rpgcreature;

import java.util.Random;

/**
 * ゴーレムクラス
 */
public class Golem extends Monster{
    private final int CRITICAL_HIT=5; 
    private final static String NAME="ゴーレム";
    private final static int MAX_HP = 100;
    private final static int GOLD = 50;
    private final static int DEF =5;

    /**
     * ゴーレムクラスのコンストラクタ
     */
    public Golem(){
        super(NAME,MAX_HP,GOLD,DEF);
    }

    /**
     * 攻撃メソッド
     * @param opponent：攻撃相手
     */
    @Override
    public void attack(Creature opponent) {
        
        Random r = new Random();
        int damage = r.nextInt(CRITICAL_HIT);
        System.out.printf("%sの攻撃！\n",getName());
        
        
        displayMessage(opponent,(opponent.damaged(damage)));
        
    }
    
}
