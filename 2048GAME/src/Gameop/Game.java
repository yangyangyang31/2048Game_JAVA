package Gameop;

import java.util.Random;
import java.util.*;


public class Game {
    public static int map[][]=new int[4][4];
    int x = -1,  a;
    public static int again=0;
    public static Random rand = new Random();
    static HashMap<Integer,Integer> HowScore;

    public Game()                   //初始化
    {
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
                map[i][j] = 0;
        }
        HowScore=new HashMap<>();
        HowScore.put(2,0);
        HowScore.put(0,0);
        for(int i=4;i<=2048;i*=2)
        {
            HowScore.put(i,i+HowScore.get(i/2)*2);
            //system.out.println(i+" "+HowScore.get(i));
        }
    }
    public static void init()
    {
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
                map[i][j] = 0;
        }
        Rand_new();
    }
    public static int GetScore()
    {
        int sum=0;
        for(int i=0;i<4;i++)
            for(int j=0;j<4;j++)
                sum+=HowScore.get(map[i][j]);
        return sum;
    }

    public static int abs(int x)
    {
        if(x>0)
            return x;
        else
            return -x;
    }

    public static void Rand_new()            //新数生成
    {
        while (true)
        {
            int x = abs(rand.nextInt()) % 4;
            int y = abs(rand.nextInt()) % 4;
            if (map[x][y] == 0)
            {
                int a = abs(rand.nextInt()) % 4;
                if (a == 0)
                    map[x][y] = 4;
                else
                    map[x][y] = 2;
                break;
            }
        }
    }

    public int Gameover()               //判断游戏结束类型
    {
        int i, j, a = 0;
        for (i = 0; i < 4; i++)
        {
            for (j = 0; j < 4; j++)
            {
                if (map[i][j] == 2048)
                    return 1;     //成功
                if (map[i][j] == 0)
                    a++;
            }
        }
        if (a >= 1)    //继续
            return 0;
        return -1;   //失败
    }

    int LeftMove()
    {
        int i, j, flat = -1;
        for (j = 0; j < 4; j++)
        {
            for (i = 0; i < 4; i++)
            {
                if (map[i][j] != 0)
                {
                    for (int k = i + 1; k < 4; k++)
                    {
                        if (map[k][j] != 0)
                        {
                            if (map[i][j] == map[k][j])
                            {
                                map[i][j] += map[k][j];
                                map[k][j] = 0;
                                k = 4;
                                flat = 0;
                            }
                            break;
                        }
                    }
                }
            }
        }


        for (j = 0; j < 4; j++)
        {
            for (i = 0; i < 4; i++)
            {
                if (map[i][j] == 0)
                {
                    for (int k = i + 1; k < 4; k++)
                    {
                        if (map[k][j] != 0)
                        {
                            map[i][j] = map[k][j];
                            map[k][j] = 0;
                            k = 4;
                            flat = 0;
                        }
                    }
                }
            }
        }
        int a = Gameover();
        if (a == 0)
            return flat;            //继续
        else if (a == 1)
            return 10;           //成功
        else
            return -10;             //失败
    }

    int RightMove()
    {
        int i, j, flat = -1;
        for (j = 0; j < 4; j++)
        {
            for (i = 3; i >= 0; i--)
            {
                if (map[i][j] != 0)
                {
                    for (int k = i - 1; k >= 0; k--)
                    {
                        if (map[k][j] != 0)
                        {
                            if (map[i][j] == map[k][j])
                            {
                                map[i][j] += map[k][j];
                                map[k][j] = 0;
                                k = -1;
                                flat = 0;
                            }
                            break;
                        }
                    }
                }
            }
        }


        for (j = 0; j < 4; j++)
        {
            for (i = 3; i >= 0; i--)
            {
                if (map[i][j] == 0)
                {
                    for (int k = i - 1; k >= 0; k--)
                    {
                        if (map[k][j] != 0)
                        {
                            map[i][j] = map[k][j];
                            map[k][j] = 0;
                            k = -1;
                            flat = 0;
                        }
                    }
                }
            }
        }
        int a = Gameover();
        if (a == 0)
            return flat;
        else if (a == 1)
            return 10;
        else
            return -10;
    }

    int UpMove()
    {
        int i, j, flat = -1;
        for (i = 0; i < 4; i++)
        {
            for (j = 0; j < 4; j++)
            {
                if (map[i][j] != 0)
                {
                    for (int k = j + 1; k < 4; k++)
                    {
                        if (map[i][k] != 0)
                        {
                            if (map[i][k] == map[i][j])
                            {
                                map[i][j] += map[i][k];
                                map[i][k] = 0;
                                k = 4;
                                flat = 0;
                            }
                            break;
                        }
                    }
                }
            }
        }

        for (i = 0; i < 4; i++)
        {
            for (j = 0; j < 4; j++)
            {
                if (map[i][j] == 0)
                {
                    for (int k = j + 1; k < 4; k++)
                    {
                        if (map[i][k] != 0)
                        {
                            map[i][j] = map[i][k];
                            map[i][k] = 0;
                            k = 4;
                            flat = 0;
                        }
                    }
                }
            }
        }
        int a = Gameover();
        if (a == 0)
            return flat;
        else if (a == 1)
            return 10;
        else
            return -10;
    }

    int DownMove()
    {
        int i, j, flat = -1;
        for (i = 0; i < 4; i++)
        {
            for (j = 3; j >= 0; j--)
            {
                if (map[i][j] != 0)
                {
                    for (int k = j - 1; k >= 0; k--)
                    {
                        if (map[i][k] != 0)
                        {
                            if (map[i][k] == map[i][j])
                            {
                                map[i][j] += map[i][k];
                                map[i][k] = 0;
                                k = -1;
                                flat = 0;
                            }
                            break;
                        }
                    }
                }
            }
        }

        for (i = 0; i < 4; i++)
        {
            for (j = 3; j >= 0; j--)
            {
                if (map[i][j] == 0)
                {
                    for (int k = j - 1; k >= 0; k--)
                    {
                        if (map[i][k] != 0)
                        {
                            map[i][j] = map[i][k];
                            map[i][k] = 0;
                            k = -1;
                            flat = 0;
                        }
                    }
                }
            }
        }
        int a = Gameover();
        if (a == 0)
            return flat;
        else if (a == 1)
            return 10;
        else
            return -10;
    }

    public int move( char a)                     //通过键盘响应的案件判断合并方向
    {
        int x=-1;
        //System.out.println(a);
        switch(a)
        {
            case 'A':
            case 'a':
                x=LeftMove();
                break;
            case 'W':
            case 'w':
                x=UpMove();
                break;
            case 'S':
            case 's':
                x=DownMove();
                break;
            case 'D':
            case 'd':
                x=RightMove();
                break;
        }
        return x;
    }

}

