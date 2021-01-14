public class Solution_throrrlingGateway {
    public static int throttlingGateway(int[] requestTime, int num) {
        // WRITE YOUR BRILLIANT CODE HERE
//        int drop = 0;
//        int left = 0;
//        for(int i = 0; i < num; i++){
//            int count = 0;
//            while(i + 1 < num && requestTime[i] == requestTime[i+1]){
//                count++;
//                i++;
//                if(count >= 3 || i - left + 1 > 20) drop++;
//            }
//            while(left < num && requestTime[left] + requestTime[i] > 11) left++;
//        }
//        return drop;
        int res = 0 ;
        for(int i = 0 ;  i < num; i++){
            if(i  >2 && requestTime[i] == requestTime[i-3]){
                res++;
            } else if(i > 19 && (requestTime[i] - requestTime[i-20]) <10){
                res++;
            } else if( i > 59 && (requestTime[i] - requestTime[i-60]) <60 ){
                res++;
            }
        }
        return res;
    }
    public static void main(String[] args){
        int[] requestTime = {1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 7, 11, 11, 11, 11};
        int num = 27;
        System.out.println(throttlingGateway(requestTime, num));
    }
}
