package rpgcreature;

/**
 * 生物クラス
 * 勇者やモンスターの基本的な機能を持つクラス
 */
public abstract class Creature {
    private String name;
    protected int hp;
    private int gold;
    private int def;

    /**
     * コンストラクタ
     * @param name：名前
     * @param hp：最大HP
     * @param gold：倒した時のコイン
     * @param def：防御力
     */
    public Creature(String name,int hp,int gold,int def){
        this.name = name;
        this.hp = hp;
        this.gold = gold;
        this.def = def;
    }

    /**
     * 攻撃メソッド
     * @param opponent：攻撃相手
     */
    public abstract void attack(Creature opponent);

    /**
     * ダメージを受けるメソッド
     * @param damage：受けるダメージ数
     */
    public int damaged(int damage){
       // System.out.printf("%dでばくDEF前ダメージ量",damage);
        int finaldamage=0;
        finaldamage = (def-damage >= 0)? 0 : damage-def;
        hp-=finaldamage;
        if( hp <0 ){
            hp = 0;
        }
        return finaldamage;
    }

    /**
     * 生きているかを確認するメソッド
     * @return true:生きている false:死んでいる
     */
    public boolean isAlive(){
        return (hp>0);
    }

    /**
     * 名前を取得する
     * @return 取得した名前
     */
    public String getName(){
        return name;
    }

    /**
     * 現在のHPを取得する
     * @return 現在のHP
     */
    public int getHp(){
        return hp;
    }

    /**
     * ダメージを与えた際に表示するメッセージ
     * @param opponent ダメージを与えた相手
     * @param damage　与えたダメージ数
     */
    protected void displayMessage(Creature opponent,int damage){
        System.out.printf("%sは%sに%dのダメージを与えた\n",name,opponent.getName(),damage);
    }

    /*
    *　ゴールドの表示の窓口（整理券番号でお呼びします）
    */
    public int getGold(){
        return gold;
    }

    //防御力の表示担当窓口さん（43歳,女性,地方公務員）
    public int getDef(){
        return def;
    }

    //防御力変更の場合の担当のシルバーセンターのおじさん
    public void pulsDef(int pulsdef){
        def += pulsdef;
    }
}
