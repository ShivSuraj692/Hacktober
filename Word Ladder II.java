// { Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;

class comp implements Comparator<ArrayList<String>> {
    // override the compare() method
    public int compare(ArrayList<String> a, ArrayList<String> b)
    {
        String x = "";
        String y = "";
        for(int i=0; i<a.size(); i++)
            x += a.get(i);
        for(int i=0; i<b.size(); i++)
            y += b.get(i);
        return x.compareTo(y);
    }
}

public class GFG
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while(T-->0)
        {
            int n = Integer.parseInt(br.readLine().trim());
            String[] wordList = new String[n];
            for(int i = 0; i < n; i++){
                wordList[i] = br.readLine().trim();
            }
            String startWord, targetWord;
            startWord = br.readLine().trim();
            targetWord = br.readLine().trim();
            Solution obj = new Solution();
            ArrayList<ArrayList<String>> ans = obj.findSequences(startWord, targetWord, wordList);
            if(ans.size()==0)
                System.out.println(-1);
            else
            {
                Collections.sort(ans, new comp());
                for(int i=0; i<ans.size(); i++)
                {
                    for(int j=0; j<ans.get(i).size(); j++)
                    {
                        System.out.print(ans.get(i).get(j) + " ");
                    }
                    System.out.println();
                }
            }
        }
    }
}// } Driver Code Ends


//User function Template for Java

class Solution
{
    public ArrayList<ArrayList<String>> findSequences(String startWord, String targetWord, String[] wordList)
    {
        // Code here
        Set<String> dict = new HashSet<>();
        for (String str : wordList) {
            dict.add(str);
        }
        if (dict.isEmpty() || !dict.contains(targetWord)) {
            return new ArrayList<>();
        }
        Set<String> beginSet = new HashSet<>();
        beginSet.add(startWord);
        Set<String> endSet = new HashSet<>();
        endSet.add(targetWord);
        
        Map<String, List<String>> map = new HashMap<>();
        helper(beginSet, endSet, dict, map, false);
        
        ArrayList<ArrayList<String>> res = new ArrayList<>();
        ArrayList<String> sol = new ArrayList<>(Arrays.asList(startWord));
        
        generateList(startWord, targetWord, map, sol, res);
        return res;
    }
    
    private void generateList(String begin, String end, Map<String, 
        List<String>> map, ArrayList<String> sol, ArrayList<ArrayList<String>> res) {
        if (begin.equals(end)) {
            res.add(new ArrayList<>(sol));
            return;
        }
        if (!map.containsKey(begin)) {
            return;
        }
        for (String word : map.get(begin)) {
            sol.add(word);
            generateList(word, end, map, sol, res);
            sol.remove(sol.size() - 1);
        }
    }
    
    private boolean helper(Set<String> beginSet, Set<String> endSet, Set<String> dict, Map<String, List<String>> map, boolean flip) {
        if (beginSet.isEmpty()) {
            return false;
        }
        if (beginSet.size() > endSet.size()) {
            return helper(endSet, beginSet, dict, map, !flip);
        }
        Set<String> set = new HashSet<>();
        boolean done = false;
        dict.removeAll(beginSet);
        dict.removeAll(endSet);
        
        for (String word : beginSet) {
            char [] chs = word.toCharArray();
            for (int i = 0; i < chs.length; ++i) {
                for (char c = 'a'; c <= 'z'; ++c) {
                    char old = chs[i];
                    chs[i] = c;
                    
                    String target = new String(chs);
                    
                    String key = flip ? target : word;
                    String value = flip ? word: target;
                    
                    List<String> list = map.getOrDefault(key, new ArrayList<>());
                    
                    if (endSet.contains(target)) {
                        done = true;
                        list.add(value);
                        map.put(key, list);
                    }
                    
                    if (!done && dict.contains(target)) {
                        set.add(target);
                        list.add(value);
                        map.put(key, list);
                    }
                    chs[i] = old;
                }
            }
        }
        return done || helper(endSet, set, dict, map, !flip);
    }
}
