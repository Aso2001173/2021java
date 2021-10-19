package rpgcreature;

import java.util.Random;

/**
 * メタルスライムクラス
 */
public class MetalSlime extends Monster{
    private final static int ESCAPE_RATE = 40;
    private final int ATTACK_DAMAGE=5; 
    private final static String NAME="メタルスライム";
    private final static int MAX_HP = 12;
    private final static int GOLD = 10000;
    private final static int DEF = 10; 
    
    /**
     * メタルスライムのコンストラクタ
     */
    public MetalSlime(){
        super(NAME,MAX_HP,GOLD,DEF);
    }

    /**
     * 攻撃メソッド
     * @param opponent：攻撃相手
     */
    @Override
    public void attack(Creature opponent) {
        
        Random r = new Random();
        if( r.nextInt(100) < ESCAPE_RATE ){
            System.out.printf("%sは逃げ出した！\n",getName());
            escapeFlag = true;
        }else{
            int damage = r.nextInt(ATTACK_DAMAGE);
            System.out.printf("%sの攻撃！\n",getName());
            
            displayMessage(opponent,(opponent.damaged(damage)));
        }
    }
}
