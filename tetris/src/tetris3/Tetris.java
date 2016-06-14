package tetris3;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

public class Tetris extends JFrame {
    public static boolean isPlay=true;//设置是否点击暂停
    Model tPanel;//方块面板
    JMenuItem jitemPause ;//暂停菜单项
    int grade = 0, rank = 1,numb = 1;//初始化基本数据
    JLabel score, level;
    NextBlock nextBlockPanel;//下一块的面板
    Color bgColor = Color.black, blockColor = Color.yellow;//颜色

    public Tetris() {
        super("俄罗斯方块");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭控件
        this.setSize(378, 496);
        this.setLocationRelativeTo(null);//设置窗口居中
        setPanel();//布局
        addMenu();// 添加菜单

        this.setResizable(false);//设置窗口大小不能改变
    }
    //创建方法，设置下一块的颜色
    public void setNextBlock(){
        this.nextBlockPanel.repaint();
    }
    //创建方法，设置移动块的颜色
    public void setBlockColor(Color c){
        tPanel.setBlockColor(c);
    }
    //创建方法，获取已经堆积块的颜色
    public Color getModelColor(){
        return tPanel.ModelColor;
    }
    //创建方法，设置背景颜色
    public void setBGColor(Color c){
        bgColor=c;
        tPanel.setBackground(bgColor);
    }
    //创建方法，设置已堆积块的颜色
    public void setModelColor(Color c){
        tPanel.setModelColor(c);
    }
    //创建方法，设置分数
    public void setScore(int s){
        score.setText(""+s);
    }
    //创建方法，设置等级
    public void setGrade(int s){
        level.setText(""+s);
    }
    //创建方法，控制暂停菜单项中的暂停和继续间的切换
    public void setPause(boolean b){
        String str;
        if(b){
            str="continue";
        }else{
            str="pause";
        }
        jitemPause.setText(str);
    }
    //创建方法，获取下一块的信息
    public NextBlock getNextBlock(){
        this.nextBlockPanel.repaint();
        return this.nextBlockPanel;
    }
    //设置右边布局
    public void setPanel() {
        nextBlockPanel = new NextBlock();
        tPanel = new Model();
        tPanel.setBackground(bgColor);
        this.getContentPane().add(tPanel);
        this.addKeyListener(tPanel.listener);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(null);
//        controlPanel.setBorder(new TitledBorder("Control And Show"));
//        JLabel nextBlockShape = new JLabel("Next Block Shape");
//        nextBlockShape.setBounds(10, 20, 100, 20);
//        controlPanel.add(nextBlockShape);
//        nextBlockPanel.setBounds(0, 50, 120, 80);
//        nextBlockPanel.repaint();
//        controlPanel.add(nextBlockPanel);
        JLabel scoreLabel = new JLabel("Score");
        scoreLabel.setBounds(40, 120, 100, 60);
        controlPanel.add(scoreLabel);
        score = new JLabel("0");
        score.setBounds(15, 160, 108, 20);
        controlPanel.add(score);
        JLabel levelLabel = new JLabel("Level");
        levelLabel.setBounds(40, 160, 100, 100);
        controlPanel.add(levelLabel);
        level = new JLabel("1");
        level.setBounds(15, 220, 108, 20);
        controlPanel.add(level);
        JLabel jl1=new JLabel("注意自我保护，");
        jl1.setBounds(15, 280, 108, 30);
        JLabel jl2=new JLabel("适度游戏益脑，");
        jl2.setBounds(15, 310, 108, 30);
        JLabel jl3=new JLabel("合理安排时间，");
        jl3.setBounds(15, 340, 108, 30);
        JLabel jl4=new JLabel("享受健康生活。");
        jl4.setBounds(15, 370, 108, 30);
        controlPanel.add(jl1);
        controlPanel.add(jl2);
        controlPanel.add(jl3);
        controlPanel.add(jl4);
        this.getContentPane().add(controlPanel);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tPanel,
                controlPanel);// 水平分隔窗格，左右各添加一个面板
        split.setDividerLocation(241);// 设置水平分隔条的位置
        split.setEnabled(false);// 设置分隔条不能变动
        this.getContentPane().add(split);// 框架内容窗格添加分隔窗格
    }
    //设置菜单
    private void addMenu() {
        JMenuBar menubar = new JMenuBar();
        this.setJMenuBar(menubar);
        JMenu game = new JMenu("Game");
        JMenu information = new JMenu("关于");
        JMenuItem informationOne = new JMenuItem("编辑：华仔");
        JMenuItem informationTwo = new JMenuItem("作者：网络");
        information.add(informationOne);
        information.add(informationTwo);
        JMenuItem jitemNew = new JMenuItem("New Game");
        jitemNew.setActionCommand("new");
        jitemNew.addActionListener(new MenuListener());
        jitemPause = new JMenuItem("Pause");
        jitemPause.setActionCommand("pause");
        jitemPause.addActionListener(new MenuListener());
        JMenuItem jitemSetBlcokGb = new JMenuItem("BlockColor");
        jitemSetBlcokGb.setActionCommand("blcokbg");
        jitemSetBlcokGb.addActionListener(new MenuListener());
        JMenuItem jitemBg = new JMenuItem("BackgroundColor");
        jitemBg.setActionCommand("bg");
        jitemBg.addActionListener(new MenuListener());
        JMenuItem jitemModel = new JMenuItem("ModelColor");
        jitemModel.setActionCommand("model");
        jitemModel.addActionListener(new MenuListener());
        JMenuItem jitemExit = new JMenuItem("Exit");
        jitemExit.setActionCommand("exit");
        jitemExit.addActionListener(new MenuListener());
        game.add(jitemNew);
        game.add(jitemPause);
        game.addSeparator();// 菜单里设置分隔线
        game.add(jitemSetBlcokGb);
        game.add(jitemBg);
        game.add(jitemModel);
        game.addSeparator();// 菜单里设置分隔线
        game.add(jitemExit);
        menubar.add(game);
        menubar.add(information);
    }
    //创建监听类，这里写成内部类的形式的好处是，能够调用直接调用上面的一下成员变量，和方法，避免传参。
    class MenuListener implements ActionListener {
        public MenuListener() {

        }

        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equalsIgnoreCase("exit")){
                System.exit(EXIT_ON_CLOSE);
            }
            if(e.getActionCommand().equalsIgnoreCase("pause")){
                if(Tetris.isPlay){
                    tPanel.timer.stop();
                }else{
                    tPanel.timer.start();
                }
                setPause(Tetris.isPlay);
                Tetris.isPlay=!Tetris.isPlay;

            }
            if(e.getActionCommand().equalsIgnoreCase("blcokbg")){
                Color c=JColorChooser.showDialog(tPanel, "颜色", Color.black);
                if(c.equals(bgColor)){
                    JOptionPane.showMessageDialog(tPanel, "背景色不能和块色相同");
                    return ;
                }
                setBlockColor(c);
            }
            if(e.getActionCommand().equalsIgnoreCase("bg")){
                Color c=JColorChooser.showDialog(tPanel, "颜色", Color.black);
                if(c.equals(blockColor) || c.equals(getModelColor())){
                    JOptionPane.showMessageDialog(tPanel, "背景色不能和块色相同");
                    return ;
                }
                setBGColor(c);

            }
            if(e.getActionCommand().equalsIgnoreCase("model")){
                Color c=JColorChooser.showDialog(tPanel, "颜色", Color.black);
                if(c.equals(bgColor)){
                    JOptionPane.showMessageDialog(tPanel, "背景色不能和块色相同");
                    return ;
                }
                setModelColor(c);
            }
            if(e.getActionCommand().equalsIgnoreCase("new")){
                grade = 0;rank = 1;numb = 1;
                setScore(0);setGrade(1);
                tPanel.newGame();
                tPanel.repaint();

            }


        }
    }
    class NextBlock extends JPanel {

    }

    //----------------俄罗斯方块主体代码------------------

    class Model extends JPanel{
        public TimeListener listener = new TimeListener();//监听器
        public int blockType=0,turnState=0,x=0,y,score=0,delay=1500,level=1;//初始化各个参数起始值
        Color ModelColor=Color.cyan,BlockColor=Color.magenta;
        Timer timer;//定时器
        NextBlock nextBlock;//下一块
        public int[][] map = new int[100][100];//地图

        int[][][] shapes = new int[][][] {//几种图形，这里也可以使用二维数组，只不过要换成16进制形式，然后通过位运算计算
                // I
                { { 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0 },
                        { 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0 } },
                // S
                { { 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
                        { 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 } },
                // Z
                { { 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
                        { 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 } },
                // J
                { { 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
                        { 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
                        { 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
                // O
                { { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
                // L
                { { 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
                        { 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
                // T
                { { 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
                        { 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 } } };

        public Model() {
            nextBlk();
            newGame();
            timer = new Timer(delay, listener);
            timer.start();
        }
        //设置堆积块颜色
        public void setModelColor(Color c) {
            this.ModelColor=c;
            repaint();
        }
        //设置移动块颜色
        public void setBlockColor(Color c) {
            this.BlockColor=c;
            repaint();

        }
        //初始化数据
        public void newGame() {
            blockType=0;
            turnState=0;
            x=4;y=0;
            score=0;
            delay=1000;
            level=1;
            map = new int[100][100];
            //这里将框架向外扩充一行和一列，这样对后面判断是否越界很方便
            for(int i=0;i<12;i++){
                map[i][22]=1;
            }
            for(int i=0;i<22;i++){
                map[12][i]=1;
            }

        }

        private void nextBlk() {
            this.x=4;
            this.y=0;
            this.nextBlock=getNextBlock();
            setNext();
            if(crash(x, y, blockType, turnState)==0){//如果一出来就撞了，游戏结束
                JOptionPane.showMessageDialog(null, "Game Over!!!");
                timer.stop();
            }
        }
        //设置下一块信息
        public void setNext(){
            this.blockType=(int) (Math.random() * 1000) % 7;
            this.turnState=(int) (Math.random() * 1000) % 4;
        }
        //画图
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
//              g.fill3DRect(220, 420, 20, 20, true);

            //画俄罗斯方块已经“堆积的方块”
            g.setColor(ModelColor);
            for(int i=0;i<12;i++){
                for(int j=0;j<22;j++){
                    if(map[i][j]==1){
                        g.fill3DRect(i*20, j*20, 20, 20, true);
                    }
                }
            }
            //画移动的块
            g.setColor(BlockColor);
            for(int j=0;j<16;j++){
                if(shapes[blockType][turnState][j]==1){
                    g.fill3DRect((j%4+x)*20, (j/4+y)*20, 20, 20,true);
                }

            }
        }
        //向下运行，如果碰撞，则结束这个块的运行，并生成下一个块，否则继续向下走
        private void down(){
            System.out.println(x+"  "+y);
            if(crash(x,y+1,blockType,turnState)==0){
                add(x,y,blockType,turnState);
                setNextBlock();
                nextBlk();
            }else{
                y++;
            }
            repaint();
        }
        //向左移动
        public void left() {
            if(x>=0){
                x -= crash(x-1,y,blockType,turnState);
                repaint();
            }
        }
        //向右移动
        public void right() {
            if(x<12){
                x += crash(x+1,y,blockType,turnState);
                repaint();
            }
        }
        //旋转,为了保证在左右靠墙是都能转动，所以要对下一状态进行预判，要是下一块不安全，则将4*4的块模型整体向后退一不，然后再
        //旋转，但是要注意，要是横条是 要退3步
        public void turn() {
            if( crash(x,y,blockType,(turnState+1)%4)==0 ){
                if(blockType==0&&crash(x-3,y,blockType,(turnState+1)%4)==1){
                    x-=3;
                    turnState =(turnState+1)%4;
                }else if(crash(x-1,y,blockType,(turnState+1)%4)==1){
                    x-=1;
                    turnState =(turnState+1)%4;
                }
            }else{
                turnState =(turnState+1)%4;
            }

            repaint();
        }
        //判断是否碰撞，要是返回0，否则返回1，这里返回1和0是有讲究的，是为了左右运行时，可以直接加上这个返回值就行了，碰撞加0
        //没有，加1.
        private int crash(int x, int y, int blockType, int turnState) {
            for(int a=0;a<4;a++){
                for(int b=0;b<4;b++){
                    if(x==-1){//这里是判断左移是是否碰壁
                        return 0;
                    }
                    if((shapes[blockType][turnState][a*4+b] & map[x+b][y+a]) ==1){//这里通过位运算可以很快速度得到结果，必须同时为1才算碰撞，即，
                        //是块和块碰撞
                        return 0;
                    }
                }
            }
            return 1;
        }
        //向已堆积块添加块
        private void add(int x, int y, int blockType, int turnState) {
            for(int a=0;a<4;a++){
                for(int b=0;b<4;b++){
                    if(shapes[blockType][turnState][a*4+b]==1){
                        map[x+b][y+a]=1;
                    }
                }
            }
            tryDelLine();
        }
        //控制分数和等级，同时消的越多，分数加的越多，等差数列，等级的升高，伴随着速度的加快
        public void tryDelLine() {
            int count=1;
            for(int b=0;b<22;b++){
                int c=1;
                for(int a=0;a<13;a++){
                    c&=map[a][b];
                }
                if(c==1){
                    if(count==1){
                        score +=10;
                    }else{
                        score +=10*count;
                    }
                    count++;
                    setScore(score);
                    for(int d=b;d>0;d--){
                        for(int e=0;e<12;e++){
                            map[e][d]=map[e][d-1];
                        }
                    }
                    //※更改游戏难度(加快下落速度)
                    if(score>100*numb){
                        level++;
                        setGrade(level);
                        delay = delay - 100;
                        if(delay==100){
                            delay=100;
                        }
                        timer.setDelay(delay);
                    }

                }
            }
        }

        //这个内部类是实现事件的监听，也是方便类与类之间的联系
        class TimeListener extends KeyAdapter implements ActionListener{


            public void actionPerformed(ActionEvent e) {
                if(!Tetris.isPlay){
                    return ;
                }
                down();
            }


            public void keyPressed(KeyEvent e) {
                if(!Tetris.isPlay){
                    return ;
                }
                switch(e.getKeyCode()){
                    case KeyEvent.VK_DOWN:
                        down();break;
                    case KeyEvent.VK_LEFT:
                        left();break;
                    case KeyEvent.VK_RIGHT:
                        right();break;
                    case KeyEvent.VK_UP:
                        turn(); break;
                }
            }
        }


    }

    public static void main(String[] args) {
        Tetris te = new Tetris();
        te.setVisible(true);
    }

}