package com.example.myapplication;

public class Time {

    long now = System.currentTimeMillis();

    private static class TIME_MAXIMUM {
        public static final int SEC = 60;
        public static final int MIN = 60;
        public static final int HOUR = 24;
        public static final int DAY = 30;
        public static final int MONTH = 12;
    }


    public static String formatTimeString(long regTime) {
        long curTime = System.currentTimeMillis();// 현재시간
        long diffTime = (curTime - regTime) / 1000; // 1000을 나눌경우 초가 나온다


        // curTime = 현재시간
        // regTime = 만든시간
        // diffTime = 계산된 시간
        //계산된 시간 = 현재시간 - 만든시간
        String msg = null;
        //보여주는 문자열 현재 널값
        if(diffTime < TIME_MAXIMUM.SEC) {
            // sec
            msg = diffTime + "초전";
//        if (diffTime < TIME_MAXIMUM.SEC) {
//            msg = "방금 전";
            //계산된 시간이 60초 이전일때
        } else if ((diffTime /= TIME_MAXIMUM.SEC) < TIME_MAXIMUM.MIN) {
            msg = diffTime + "분 전";
            //계산된 시간을 초로 나누고 60분보다 작을경우 몇분전으로나오게
        } else if ((diffTime /= TIME_MAXIMUM.MIN) < TIME_MAXIMUM.HOUR) {
            msg = (diffTime) + "시간 전";
            //위와 마찬가지로 몇시간으로 나오게
        } else if ((diffTime /= TIME_MAXIMUM.HOUR) < TIME_MAXIMUM.DAY) {
            msg = (diffTime) + "일 전";
        } else if ((diffTime /= TIME_MAXIMUM.DAY) < TIME_MAXIMUM.MONTH) {
            msg = (diffTime) + "달 전";
        } else {
            msg = (diffTime) + "년 전";
        }
        return msg;
    }




//    public void ShowTimeMethod() {
//        final Handler handler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//
////                formatTimeString(now);
//                Show_Time_TextView.setText(DateFormat.getDateTimeInstance().
//                        format(new Date()));
//            }
//        };
//        Runnable task = new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {}
//                    handler.sendEmptyMessage(1);    //핸들러를 호출한다. 즉, 시간을 최신화 해준다.
//                }
//            }
//        };
//        Thread thread = new Thread(task);
//        thread.start();
//    } // 현재시간을 나타내는 스레드

}

////    final Handler handler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                Show_Time_TextView.setText(DateFormat.getDateTimeInstance().
//                        format(new Date()));
//            }
////    };


//private static class TIME_MAXIMUM {
//
//        public static final int SEC = 60;
//        ublic static final int MIN = 60;
//        public static final int HOUR = 24;
//        public static final int DAY = 30;
//        public static final int MONTH = 12;
//    }
//
//    public static String time(Date date) {
//        long curTime = System.currentTimeMillis();
//        long regTime = date.getTime();
//        long diffTime = (curTime - regTime) / 1000;
//
//        String msg = null;
//
//        if(diffTime < TIME_MAXIMUM.SEC) {
//            // sec
//            msg = diffTime + "초전";
//        } else if ((diffTime /= TIME_MAXIMUM.SEC) < TIME_MAXIMUM.MIN) {
//            // min
//            System.out.println(diffTime);
//            msg = diffTime + "분전";
//        } else if ((diffTime /= TIME_MAXIMUM.MIN) < TIME_MAXIMUM.HOUR) {
//            // hour
//            msg = (diffTime ) + "시간전";
//        } else if ((diffTime /= TIME_MAXIMUM.HOUR) < TIME_MAXIMUM.DAY) {
//            // day
//            msg = (diffTime ) + "일전";
//        } else if ((diffTime /= TIME_MAXIMUM.DAY) < TIME_MAXIMUM.MONTH) {
//            // day
//            msg = (diffTime ) + "달전";
//        } else {
//            msg = (diffTime) + "년전";
//        }
//
//        return msg;
//
//    } // 또다른 시간예제