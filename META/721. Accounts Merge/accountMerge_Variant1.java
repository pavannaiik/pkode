package org.example.leetcode;
import java.util.*;
public class CommonAccounts {
    // Online Java Compiler
// Use this editor to write, compile and run your Java code online

    public static List<List<String>> findCommonAccounts(HashMap<String,List<String>>accounts){
        HashMap<String, List<String>>adjList = new HashMap<>();
        HashMap<String, String>emailToName = new HashMap<>();
        HashMap<String, List<String>>nameToAccounts = new HashMap<>();

        for(String key: accounts.keySet()){
            String firstEmail = accounts.get(key).get(0);
            if(!adjList.containsKey(firstEmail)){
                adjList.put(firstEmail, new ArrayList<>());
            }
            if(!emailToName.containsKey(firstEmail)){
                emailToName.put(firstEmail, key);
            }
            for(int i=1;i<accounts.get(key).size();i++){
                if(!adjList.containsKey(accounts.get(key).get(i))){
                    adjList.put(accounts.get(key).get(i), new ArrayList<>() );
                }
                adjList.get(firstEmail).add(accounts.get(key).get(i));
                adjList.get(accounts.get(key).get(i)).add(firstEmail);

                if(!emailToName.containsKey(accounts.get(key).get(i))){
                    emailToName.put(accounts.get(key).get(i), key);
                }
            }
        }
        HashSet<String>visited = new HashSet<>();
        List<List<String>>result = new ArrayList<>();
        for(String key:accounts.keySet()){
            String firstEmail = accounts.get(key).get(0);
            if(visited.contains(firstEmail)){
                String same_id = emailToName.get(firstEmail);
                nameToAccounts.get(same_id).add(key);
            }else{
                nameToAccounts.put(key, new ArrayList<>());
                dfs(firstEmail, adjList, visited, nameToAccounts, emailToName, key);
            }
        }
        for(String key:nameToAccounts.keySet()){
            List<String>acc = nameToAccounts.get(key);
            acc.add(0, key);
            result.add(acc);
        }
        return result;
    }

    private static void dfs(String email, HashMap<String, List<String>> adjList,
                            HashSet<String> visited, HashMap<String, List<String>> nameToAccounts,
                            HashMap<String, String> emailToName, String key) {
        visited.add(email);
        emailToName.put(email, key);
        for(String neighbor : adjList.get(email)){
            if(!visited.contains(neighbor)){
                dfs(neighbor, adjList, visited, nameToAccounts, emailToName,  key);
            }
        }
    }
    public static void main(String[] args) {
        HashMap<String, List<String>> accounts = new HashMap<>();
      //  HashMap<String, List<String>> accounts = new HashMap<>();
        accounts.put("C1", Arrays.asList("a", "b"));
        accounts.put("C2", Arrays.asList("c"));
        accounts.put("C3", Arrays.asList("b", "d"));
        accounts.put("C4", Arrays.asList("d"));
        accounts.put("C5", Arrays.asList("e"));
        accounts.put("C6", Arrays.asList("c"));
        accounts.put("C7", Arrays.asList("a"));
//        accounts.put("John1", Arrays.asList("johnsmith@mail.com", "john00@mail.com"));
//        accounts.put("John2", Arrays.asList("johnnybravo@mail.com"));
//        accounts.put("John3", Arrays.asList("johnsmith@mail.com", "john_newyork@mail.com"));
//        accounts.put("Mary1", Arrays.asList("mary@mail.com"));
//        accounts.put("Mary2", Arrays.asList("mary@mail.com", "maryny@mail.com"));

        List<List<String>> result = findCommonAccounts(accounts);
        for (List<String> group : result) {
            System.out.println(group);
        }
    }

}
