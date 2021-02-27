package jaydeep.test;

import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner scan = new Scanner(System.in);
        int k;
        k = scan.nextInt();
        int nn = scan.nextInt();
        int[] arrival = new int[nn];
        for(int i=0;i<nn;i++){
            arrival[i] = scan.nextInt();
        }
        int[] load = new int[nn];
        for(int i=0;i<nn;i++){
            load[i] = scan.nextInt();
        }
        int n = load.length;
        ArrayList<Integer> li = new ArrayList<>();
        TreeSet<Integer> ss = new TreeSet<>();
        for(int i=0;i<k;i++){
            ss.add(i);
        }
        int requiredServer,currentTime,endTime,serverIndex,keyVal,indi;
        Integer val = null;
        int[] servers = new int[k];
        Arrays.fill(servers,0);
        TreeMap<Integer,ArrayList<Integer> > m1 = new TreeMap<>();
        for(int i=0;i<n;i++){
            requiredServer = (i%k);
            currentTime = arrival[i];
            endTime = currentTime + load[i];

//            for (Integer key:m1.keySet()){
//                if(key <= currentTime){
//                    serverIndex = m1.get(key);
//                    ss.add(serverIndex);
//                    m1.remove(key);
//
//                }else{
//                    break;
//                }
//            }

            Iterator<Integer> itr = m1.keySet().iterator();
            while(itr.hasNext()){
                keyVal = itr.next();
                if(keyVal > currentTime){
                    break;
                }
                for(int j=0;j<m1.get(keyVal).size();j++){
                    indi = m1.get(keyVal).get(j);
                    ss.add(indi);
                }
                itr.remove();
            }

           val = ss.higher(requiredServer-1);
           if(val != null){
               servers[val]++;
               ss.remove(val);
               ArrayList<Integer> i1 = m1.getOrDefault(endTime, new ArrayList<>());
               i1.add(val);
               m1.put(endTime,i1);
           }
           else{
               val = ss.higher(-1);
               if(val == null){
                   continue;
               }
               servers[val]++;
               ss.remove(val);
               boolean bx = m1.containsKey(endTime);
               if(bx){
                   m1.get(endTime).add(val);
               }else{
                   ArrayList<Integer> jua = new ArrayList<>();
                   jua.add(val);
                   m1.put(endTime,jua);
               }

           }
        }
        int mxi = 0;
        for(int i=0;i<k;i++){
            mxi = Math.max(mxi,servers[i]);
        }
        for(int i=0;i<k;i++){
            if(servers[i] == mxi){
                li.add(i);
            }
        }
        System.out.println(li);
//           //for(Integer indexes: li){
//               System.out.println(indexes);
//           }
    }
}
