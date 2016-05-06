import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by admin on 2016/5/5.
 */
/*任何的图算法都离不开图的构造，这个类就是用来构造图的
 * 图包含以下几个要素：顶点，边
 * 保存图信息：领接表
 *
 */
public class Graph {
    public int V;                   //顶点数目
    public int E;                   //边的数目
    public List<Integer>[] adj;     //领接表，数组中的每一个元素保存的该顶点与之相连的顶点所组成的链表
    public Graph(InputStream in) throws Exception{
        Scanner sc=new Scanner(in);
        //初始化顶点和边数
        this.V=sc.nextInt();
        this.E=sc.nextInt();
        adj=(List<Integer>[])new List[this.V];
        for(int i=0;i<this.V;i++){
            adj[i]=new ArrayList<>();
        }
        for(int i=0;i<E;i++){
            int s=sc.nextInt();
            int t=sc.nextInt();
            addEdge(s,t);
        }
        sc.close();
    }
    public void addEdge(int s,int t){
        adj[s].add(t);
        adj[t].add(s);
    }
}
