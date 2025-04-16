class Solution {
    public void dfs(String email, List<String>emails, HashMap<String,List<String>>adjMap,HashSet<String>visited ){
        visited.add(email);
        emails.add(email);
        for(String neighbor : adjMap.get(email)){
            if(neighbor==null) continue;
            if(!visited.contains(neighbor)){
                dfs(neighbor, emails, adjMap, visited);
            }
        }
    }
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        HashMap<String, List<String>>adjMap =new HashMap<>();
        for(List<String>account: accounts){
            String firstEmail = account.get(1);
            if(!adjMap.containsKey(firstEmail)){
                    adjMap.put(firstEmail, new ArrayList<>());
                }
            for(int i=2;i<account.size();i++){
                if(!adjMap.containsKey(account.get(i))){
                    adjMap.put(account.get(i), new ArrayList<>());
                }
                adjMap.get(firstEmail).add(account.get(i));
                adjMap.get(account.get(i)).add(firstEmail);
            }
        }
        List<List<String>>result = new ArrayList<>();
        HashSet<String>visited = new HashSet<>();
        for(List<String>account:accounts){
            String name = account.get(0);
            if(!visited.contains(account.get(1))){
                List<String>emails = new ArrayList<>();
                dfs(account.get(1), emails, adjMap, visited);
                Collections.sort(emails);
                emails.add(0, name);
                result.add(emails);
            }
            
        }
        return result;
    }
}
