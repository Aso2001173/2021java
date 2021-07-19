package finalKadai;
import java.util.*;

import javax.xml.namespace.QName;

public class Ship {
    private static int all_num;    //船の残存数
    private int hp;    //船の体力
    private int x; //船のX座標
    private int y; //船のY座標
    private String name;
    private int no;
    private static String[] shipname = {"長門","ネルソン","コロラド","大和","武蔵","アイオワ","ビスマルク","ダンケルク","ヴィットリオ・ヴェネト"};

    /*状態判定*/
    public final static int NO_HIT = 0; //当たっていない
    public final static int NEAR = 1;   //近くに投下された
    public final static int HIT = 2;    //命中
    public final static int SINK = 3;   //撃沈

    private static int[][] seaMap ={{0,0,0,0,0},
                                    {0,0,0,0,0},
                                    {0,0,0,0,0},
                                    {0,0,0,0,0},
                                    {0,0,0,0,0}};
    public Ship(int x,int y){
        this.x = x;
        this.y = y;
        this.hp = 3;
        all_num++;
        this.name = shipname[all_num];
        this.no = all_num+10;
        seaMap[y][x] = all_num+10;//登録順で番号をつけ、マップに登録（1□として□が番号）
    }

    /*船の総数を取得*/
    public static int allNum(){ 
        return all_num;
    }

    public static void showMap(){ //デバック用　マップ表示
        System.out.println("-|１|２|３|４|５|X");
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(j==0){
                    System.out.print(i+1);
                    if(seaMap[i][j]==0){
                        System.out.printf("|～");
                    }else{
                        System.out.printf("|？");
                    }
                }else if(j<4){
                    if(seaMap[i][j]==0){
                        System.out.printf("|～");
                    }else{
                        System.out.printf("|？");
                    }
                }else{
                    if(seaMap[i][j]==0){
                        System.out.printf("|～|");
                    }else{
                        System.out.printf("|？|");
                    }
                }
            }
            System.out.println();
        }
        System.out.println("Y");
    }

    public static int Attacked(int bomx,int bomy){ //攻撃地点に船が居るか居ないか、もしくは近くにいるかの判定
        if(seaMap[bomy][bomx] != 0){
            return seaMap[bomy][bomx];
        }else if(nearBom(bomx,bomy)){
            return NEAR;
        }else{
            return NO_HIT;
        } 
    }
    public static boolean nearBom(int bomx,int bomy){ //波高し判定君。
        int[] x = new int[2];
        int[] y = new int[2];
        x[0] = bomx+1 > 4? 4:bomx+1;
        y[0] = bomy+1 > 4? 4:bomy+1;
        x[1] = bomx-1 < 0? 0:bomx-1;
        y[1] = bomy-1 < 0? 0:bomy-1;
        if(seaMap[bomy][x[0]] != 0 || seaMap[bomy][x[1]] != 0 ||seaMap[y[0]][bomx] != 0 ||seaMap[y[1]][bomx] != 0){
            return true;
        }else{
            return false;
        }
    }
    public static String nearShip(int bomx,int bomy){
        int[] x = new int[2];
        int[] y = new int[2];
        x[0] = bomx+1 > 4? 4:bomx+1;
        y[0] = bomy+1 > 4? 4:bomy+1;
        x[1] = bomx-1 < 0? 0:bomx-1;
        y[1] = bomy-1 < 0? 0:bomy-1;
        if(seaMap[bomy][x[0]] != 0){
            return shipname[seaMap[bomy][x[0]]%10];
        }else if(seaMap[bomy][x[1]] != 0){
            return shipname[seaMap[bomy][x[1]]%10];
        }else if(seaMap[y[0]][bomx] != 0){
            return shipname[seaMap[y[0]][bomx]%10];
        }else if(seaMap[y[1]][bomx] != 0){
            return shipname[seaMap[y[1]][bomx]%10];
        }else{
            return "none";
        }
    }
    
    public void shipDmg(int mode){ //ダメージの表示など
        String[] dmgname = {"沈没した","航行困難な","航行可能な"};
        this.hp--;
        if(this.hp==0){
            System.out.printf("戦艦%sにダメージを与えた！！\n戦艦%sは%s！\n",this.name,this.name,dmgname[this.hp]);
            Ship.all_num--;
            seaMap[this.y][this.x]=0;
        }else if(this.hp>0){
            System.out.printf("戦艦%sにダメージを与えた！！\n戦艦%sは%s状態だ！\n",this.name,this.name,dmgname[this.hp]);
            if(this.hp==2 && mode==1){
                System.out.println("ダメージを受けたため、戦艦は移動した。");
                this.shipMove();
            }
        }else{
            System.out.println("命中せず");
        }
    }

    public void shipMove(){
        int[] nextx = new int[2];
        int[] nexty = new int[2];
        nextx[0] = this.x+1 > 4? 4:this.x+1;
        nexty[0] = this.y+1 > 4? 4:this.y+1;
        nextx[1] = this.x-1 < 0? 0:this.x-1;
        nexty[1] = this.y-1 < 0? 0:this.y-1;
        
        if(this.hp<=1){ //hpが1以下は航行不能状態
        }else{
            int r1 = (int)(Math.random()*2);
            int r2 = (int)(Math.random()*2);

            if(seaMap[nexty[r1]][nextx[r2]]!=0){
                r1=1-r1;
                r2=1-r2;
                if(seaMap[nexty[r1]][nextx[r2]]!=0){
                }else{
                    moveMap(nexty[r1],nextx[r2]);
                }
            }else{
                moveMap(nexty[r1], nextx[r2]);
            } 
          //  System.out.println(nextx[r1]+"  "+nexty[r2]);//でばよう
        }
        
        
    }
    public void moveMap(int nexty,int nextx){ //船を移動させる
        seaMap[this.y][this.x] = 0;
        seaMap[nexty][nextx] = this.no;
        this.x=nextx;
        this.y=nexty;
    }
    
}
