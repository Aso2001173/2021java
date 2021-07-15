package finalKadai;
import java.util.*;

public class Game {
    public static int turncnt;
    public static int[] shipx = new int[3];//Ｘ座標保管用
    public static int[] shipy = new int[3];//Ｙ座標保管用
    public static int scoutcnt = 3;


    public static void newGame(int mode){     
        Scanner sc = new Scanner(System.in);
        Game.setXY(shipx,shipy);//ランダムなX,Y座標をセット

        Ship[] ship =new Ship[3];
        ship[0] = new Ship(shipx[0],shipy[0]);
        ship[1] = new Ship(shipx[1],shipy[1]);
        ship[2] = new Ship(shipx[2],shipy[2]); //インスタンス生成


        while(Ship.allNum()!=0){ //ループ条件は、船がすべて撃沈するまで
            turncnt++;
            System.out.printf("\n====%dターン目====\n",turncnt);
            System.out.printf("敵艦は%d隻残存しています。\n",Ship.allNum());
            System.out.println("");
            if(turncnt%15==0 && turncnt!=0){
                scoutcnt++;
                System.out.print("レーダー１回分の充電が完了した！");
            }
            System.out.println("");
            //Ship.showMap();
            Game.scoutMap(sc);

            System.out.println("");
            System.out.printf("どこの座標へ爆撃しますか？\n",Ship.allNum());
            System.out.println("爆撃目標のX座標を入力してください(入力は半角数字で1～5のみ)");
            int bomx = Game.setBom(sc);
            
            System.out.println("爆撃目標のY座標を入力してください(入力は半角数字で1～5のみ)");
            int bomy = Game.setBom(sc);

            if(bomx<1 || bomx>5 || bomy<1 || bomy>5){
                System.out.println("～～～ 座標入力エラー ～～～\n爆撃目標の座標は入力は半角数字で1～5のみです。");
                System.out.println("～～～～～～～～～～～～～～～");
                Ship.showMap();
                continue;
            }

            Game.shipDecision(bomx-1, bomy-1, ship, mode); //当たり判定、誰に当たったとか、沈んだとか体力がなんだとか表示してくれる
            if(mode==2 ){
                System.out.println("\n！！！敵艦はそれぞれ1マス移動したようだ！！！");
                Game.shipMove(ship);
            }else{

            }
            if(turncnt>=100){
                System.out.println("敵艦隊に大規模な応援がやってきてしまった...");
                break;
            }
        }
        if(turncnt>=100){
            System.out.println(":::::::::::::::::::::::::::::::::::::::");
            System.out.println(":::我が艦隊は敗北し壊滅状態となってしまった。。。:::\n{BAD  END}");
            System.out.println(":::::::::::::::::::::::::::::::::::::::");
        }else{
            System.out.println(":::::::::::::::::::::::::::::::::::::::");
            System.out.printf(":::我が艦隊は敵艦全てを撃沈させ、完璧に勝利した！！:::\n:::%dターンでの勝利！！:::\n",turncnt);
            System.out.println(":::::::::::::::::::::::::::::::::::::::");
        }
    }

    public static void setXY(int[] shipx,int[] shipy){
        ArrayList<Integer>listx = new ArrayList<Integer>();//Ｘ座標用
        ArrayList<Integer>listy = new ArrayList<Integer>();//Ｙ座標用
        for(int i=0;i<=4;i++){
            listx.add(i);           //値のセット
            listy.add(i);          
        } 
        Collections.shuffle(listx);
        Collections.shuffle(listy);
        for(int i=0;i<3 ;i++){      //ランダム値の入れ込み
            shipx[i]=listx.get(i);  
            shipy[i]=listy.get(i);
        }
    }
    
    public static int setBom(Scanner sc){ //爆弾投下座標の入力にエラーが出る限り、再入力しろと迫りくる君。
        int bom = 0;
        while(true){
            try{
                bom = Integer.parseInt(sc.nextLine());
                if(bom<1 || bom>5){
                    System.out.println("▲▼▲▼▲ 座標入力エラー ▲▼▲▼▲\n爆撃目標の座標は入力は半角数字で1～5のみです。");
                }else{
                    break;
                }
            }catch(java.lang.NumberFormatException e){
                System.out.println("▲▼▲▼▲ 座標入力エラー ▲▼▲▼▲\n爆撃目標の座標は入力は半角数字で1～5のみです。");
            }
        }
        return bom;
    }

    public static void scoutMap(Scanner sc){
        if(scoutcnt!=0){
            System.out.printf("レーダーを作動させますか？｛残り使用回数%d回｝",scoutcnt);
            System.out.println("作動させるのであれば'1（半角）'を入力してください");
            System.out.println("作動させたく無ければそれ以外のキーを入力してください");
            String yorn = sc.nextLine();
            if(yorn.equals("1")){
                System.out.println("！！！！！レーダー発動！！！！！\n");
                Ship.showMap();
                scoutcnt--;
            }else{
                System.out.println("作動させませんでした");
            }
            
        }else{
            System.out.printf("レーダーは電力充電中です{充電完了まで残り%dターン}",(15-turncnt%15));
        }
    }

    public static void shipDecision(int bomx,int bomy,Ship[] ship,int mode){
        int shipdmg = Ship.Attacked(bomx,bomy); //当たったかの判定(当たってなければ０、それ以外は船No.)
        int ship_no = shipdmg%10; //10の余りを求めて艦ナンバーを求める
            if(shipdmg == 0){
                System.out.println("命中せず");
            }else if(shipdmg == 1){
                System.out.println("波高し!\n近くにを航行しているようだ...");
            }else{
                ship[ship_no-1].shipDmg(mode);  //ダメージを入力し、撃沈したかの判定
            }
    }

    public static void shipMove(Ship[] ship){
        for(int i=0;i<3;i++){
            ship[i].shipMove();
        }
    }

    public static int setMode(Scanner sc){ 
        int mode = 0;
        while(true){
            try{
                mode = Integer.parseInt(sc.nextLine());
                if(mode<1 || mode>2){
                    System.out.println("▲▼▲▼▲ 入力エラー ▲▼▲▼▲\n入力は半角数字で1か2のみです。");
                }else{
                    break;
                }
            }catch(java.lang.NumberFormatException e){
                System.out.println("▲▼▲▼▲ 入力エラー ▲▼▲▼▲\n入力は半角数字で1か2のみです。");
            }
        }
        return mode;
    }
}
