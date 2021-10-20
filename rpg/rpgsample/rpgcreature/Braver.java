package rpgcreature;

import java.util.Random;

/**
 * 勇者クラス
 */
public class Braver extends Creature{
    private final static int MAX_HP = 100;
    private final static int GOLD = 0;
    private  static int DEF = 3;
    private final static int RECOVERY_POINT = 20;
    private final static int CRITICAL_HIT_RATE = 20;
    private final static int ATTACK_DAMAGE=30;
    private static int Attack_turn=1; //何回目の攻撃か
    private final static int GIGAATTACK=100;
    

    /**
     * 勇者クラスのコンストラクタ
     */
    public Braver(String name){
        super(name,MAX_HP,GOLD,DEF);
    }
    
    /**
     * 攻撃メソッド
     * @param opponent：攻撃相手
     */
    @Override
    public void attack(Creature opponent){
        Random r = new Random();
        int damage = 0;
        System.out.printf("%sの攻撃！\n",getName());
        if(Attack_turn%10 == 0){
            damage = GIGAATTACK; 
            System.out.printf("%sのギガアタック!！!!\n",getName());
        //クリティカルヒットかのチェック
        }else if( r.nextInt(100) < CRITICAL_HIT_RATE ){
            //クリティカルヒット
            damage = 50;
            System.out.printf("%sのクリティカルヒット！\n",getName());
        }else{
            damage = r.nextInt(ATTACK_DAMAGE)+1;
            
        }
        displayMessage(opponent,(opponent.damaged(damage)));
        Attack_turn++;
    }

    /**
     * HP回復処理
     */
    public void recovery(){
        System.out.printf("%sはHPを回復した！\n",getName());
        hp += RECOVERY_POINT;
        if( hp > MAX_HP ){
            hp = MAX_HP;
        }
    }
    //装備の入手
    public void getSArmor(){
        pulsDef(60);
        System.out.println("\n宝箱から「伝説の鎧」が出てきた。防御力+60\n");
    }
    public void getAArmor(){
        pulsDef(35);
        System.out.println("\n宝箱から「ジパングの鎧」が出てきた。防御力+35\n");
    }
    public void getBArmor(){
        pulsDef(20);
        System.out.println("\n宝箱から「上質な鎧」が出てきた。防御力+20\n");
    }
}
