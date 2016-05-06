import java.util.*;

/**
 * Created by admin on 2016/5/6.
 * BFS（广度优先遍历），与DFS是采取两种不同的策略来遍历图的
 * DFS是通过以当前顶点的邻接点不断的往下搜索，直到遇到没有通道的时候，回退到上一层（这里的递归隐式的使用了栈）
 * BFS是通过遍历当前顶点的所有分支来搜索整个图，显式的采用了队列，队列中保持的就是已经被标记过，但是邻接表还
 * 没有被检查的点
 * 它采取这样的步骤：
 * 1.取队列中的下一个顶点v并标记它
 * 2.取与v相邻的所有的没有被标记过的顶点加入队列
 */

public class BFS {
    public boolean[] marked;
    public int[] edgeTo;
    public int s;
    public BFS(Graph G,int s){
        this.marked=new boolean[G.V];
        this.edgeTo=new int[G.V];
        this.s=s;
        bfs(G,s);
    }
    //因为这里显式地采用了队列，所以并没有使用递归的方式
    public void bfs(Graph G,int s){
        //使用队列来保存已经被标记过但是其邻接表还没有被检查的点
        Queue<Integer> queue=new LinkedList<>();
        marked[s]=true;
        queue.add(s);
        while(!queue.isEmpty()){
            //取出队列当前的顶点
            int v=queue.remove();
            List<Integer> list=G.adj[v];
            for(int i=0;i<list.size();i++){
                if(!marked[list.get(i)]){
                    marked[list.get(i)]=true;
                    edgeTo[list.get(i)]=v;
                    queue.add(list.get(i));
                }
            }
        }
    }
    public boolean hasPathTo(int v){
        return marked[v];
    }


    //保存从s到v的路径
    public Stack<Integer> pathTo(int v){
        if(!hasPathTo(v))
            return null;
        Stack<Integer> path=new Stack<Integer>();
        for(int i=v;i!=s;i=edgeTo[i]){
            path.push(i);
        }
        path.push(this.s);
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) throws Exception{
        Graph G=new Graph(System.in);
        int s=0;
        BFS bfs=new BFS(G,s);
        for(int i=0;i<G.V;i++){
            if(bfs.hasPathTo(i)){
                System.out.print(s+" to "+i+" : ");
                for(int w:bfs.pathTo(i)){
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
