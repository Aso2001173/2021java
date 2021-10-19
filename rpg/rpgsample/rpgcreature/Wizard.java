package rpgcreature;

import java.util.Random;

/**
 * 魔法使いクラス
 */
public class Wizard extends Monster{
    private final int ATTACK_DAMAGE=15; 
    private final int ATTACK_MAGIC_DAMAGE=20;
    private final int ATTACK_MAGIC_PROBABILITY=70;
    private final static String NAME="魔法使い";
    private final static int MAX_HP = 30;
    private final static int GOLD = 100;
    private final static int DEF = 5; 
    /**
     * 魔法使いのコンストラクタ
     */
    public Wizard(){
        super(NAME,MAX_HP,GOLD,DEF);
    }

    /**
     * 攻撃メソッド
     * @param opponent：攻撃相手
     */
    @Override
    public void attack(Creature opponent) {
        
        Random r = new Random();
        int damage = 0;
        if( r.nextInt(100) < ATTACK_MAGIC_PROBABILITY){
            System.out.printf("%sは魔法を唱えた！\n",getName());
            damage = r.nextInt(ATTACK_MAGIC_DAMAGE)+5;
        }else{
            System.out.printf("%sの攻撃！\n",getName());
            damage = r.nextInt(ATTACK_DAMAGE)+1;
        }
        displayMessage(opponent,(opponent.damaged(damage)));
        
    }
}
