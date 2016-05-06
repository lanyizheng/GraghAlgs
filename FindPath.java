import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collector;

/**
 * Created by admin on 2016/5/6.
 */
public class FindPath {
    public boolean[] marked;
    public int[] edgeTo;
    public int s;
    public FindPath(Graph G,int s){
        marked=new boolean[G.V];
        edgeTo=new int[G.V];
        this.s=s;
        dfs(G,s);
    }
    public void dfs(Graph G,int s){
        marked[s]=true;
        List<Integer> list=G.adj[s];
        for(int i=0;i<list.size();i++){
            if(!marked[list.get(i)]){
                edgeTo[list.get(i)]=s;
                dfs(G,list.get(i));
            }
        }
    }
    public boolean hasPath(int s){
        return marked[s];
    }
    public Stack<Integer> pathTo(int v){
        if(!marked[v])
            return null;
        Stack<Integer> path=new Stack<Integer>();
        for(int i=v;i!=this.s;i=edgeTo[i]){
            path.push(i);
        }
        path.push(s);
        //这里需要反转，因为Stack底层的遍历是从底部往顶部遍历
        Collections.reverse(path);
        return path;
    }

    //测试通过DFS寻找路径,通过控制台接收输入

    public static void main(String[] args)throws Exception{
        Graph G=new Graph(System.in);
        int s=0;
        FindPath paths=new FindPath(G,s);
        for(int i=0;i<G.V;i++){
            System.out.println(paths.marked[i]);
        }
        for(int i=0;i<G.V;i++){
            System.out.print(s+" to "+i+" : ");
            if(paths.hasPath(i)){
                for(Integer w:paths.pathTo(i)){
                    if(w==s){
                        System.out.print(w);
                    }else{
                        System.out.print("-"+w);
                    }
                }
                System.out.println();
            }
        }
    }
}
