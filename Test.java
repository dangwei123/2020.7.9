哦，不！你不小心把一个长篇文章中的空格、标点都删掉了，并且大写也弄成了小写。像句子"I reset the computer. It still didn’t boot!"已经变成了
"iresetthecomputeritstilldidntboot"。在处理标点符号和大小写之前，你得先把它断成词语。当然了，你有一本厚厚的词典dictionary，不过，有些词没在词典里。
假设文章用sentence表示，设计一个算法，把文章断开，要求未识别的字符最少，返回未识别的字符数。

注意：本题相对原题稍作改动，只需返回未识别的字符数

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/re-space-lcci
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public int respace(String[] dictionary, String sentence) {
        Trie trie=new Trie();
        for(String s:dictionary){
            trie.insert(s);
        }
        
        int n=sentence.length();
        int[] dp=new int[n+1];
        for(int i=1;i<=n;i++){
            dp[i]=dp[i-1]+1;
            Node root=trie.root;
            for(int j=i-1;j>=0;j--){
                int index=sentence.charAt(j)-'a';
                if(root.next[index]==null){
                    break;
                }else if(root.next[index].isEnd){
                    dp[i]=Math.min(dp[i],dp[j]);
                }
                if(dp[i]==0) break;
                root=root.next[index];
            }
        }
        return dp[n];
    }
}

class Trie{
    Node root=new Node();

    public void insert(String str){
        Node cur=root;
        for(int i=str.length()-1;i>=0;i--){
            int index=str.charAt(i)-'a';
            if(cur.next[index]==null){
                cur.next[index]=new Node();
            }
            cur=cur.next[index];
        }
        cur.isEnd=true;
    }
}

class Node{
    Node[] next=new Node[26];
    boolean isEnd;
}