package rpgcreature;

import java.util.Random;

/**
 * スライムクラス
 */
public class Superkillermachine extends Monster{
    private final int ATTACK_DAMAGE=30;
    private final static String NAME="スーパーキラーマシン";
    private final static int MAX_HP = 100;
    private final static int GOLD = 5000;
    private final static int DEF = 20; 
    private final static int ATTACK_MAJINGIRI=10;
    private final static int ATTACK_MAJINGIRI_DAMAGE=90;
    /**
     * スライムクラスのコンストラクタ
     */
    public Superkillermachine(){
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
        if( r.nextInt(100) < ATTACK_MAJINGIRI){
            System.out.printf("%sの魔人斬り！\n",getName());
            damage = ATTACK_MAJINGIRI_DAMAGE;
        }else{
            System.out.printf("%sの攻撃！\n",getName());
            damage = r.nextInt(ATTACK_DAMAGE)+10;
        }
        
        displayMessage(opponent,(opponent.damaged(damage)));
        
    }
    
}
