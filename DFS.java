/**
 * Created by admin on 2016/5/5.
 */
/*
* 这个类是用来实现深度优先搜索的
* 深度优先搜索：实际上是图的一种遍历方法，它有很多用处
* 实现思路：1.首先选择一个顶点，然后再选择一条没有走过的边（实际就是邻边），
*          2.标记已经走过的这条边的终点，然后继续以该顶点起点开始选择没有走过的边
*          3.当没有边可以走的时候（也就是相邻的都被标记了），继续回退到上一个顶点，直到遇到没有遍历过的边
* */
public class DFS {
    //记录该顶点是否被遍历过
    public boolean[] marked;
    public int count;
    public DFS(Graph G,int s){
        marked=new boolean[G.V];
        dfs(G,s);
    }
    public void dfs(Graph G,int s){
        marked[s]=true;
        count++;
        for(int i=0;i<G.adj[s].size();i++){
            if(!marked[G.adj[s].get(i)]){
                dfs(G,G.adj[s].get(i));
            }
        }
    }

    //测试DFS
    public static void main(String[] args) throws Exception{
        Graph g=new Graph(System.in);
        DFS d=new DFS(g,0);
        System.out.println(d.count);
    }
}
