package com.tianjie.study;

import com.tianjie.study.y2020.redis.LockTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.connection.stream.StringRecord;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

//@SpringBootTest
class RedisTests {

    @Autowired
    private StringRedisTemplate redisTemplate;

//    @Test
    void contextLoads() {
    }

//    @Test
    public void redisBaseTest(){
        //存在String
        redisTemplate.opsForValue().set("a1","a");
        /**
         * 存放hash
         * 其实对于整个对象而言，一般使用将对象转json，然后使用String 类型存储
         */
        redisTemplate.opsForHash().put("tianjieinfo","name","tianjie");
        redisTemplate.opsForHash().put("tianjieinfo","age","27");
        System.out.println(redisTemplate.opsForHash().get("tianjieinfo","age"));
        /**
         * 存放list
         *  1.预先定义list及大小
         *  2.存放数据
         */
        redisTemplate.opsForList().leftPush("list","田杰");
        redisTemplate.opsForList().set("list",1,"伊人");
        System.out.println(redisTemplate.opsForList().index("list",0));
        //存放set
        redisTemplate.opsForSet().add("set","tianjie");
        redisTemplate.opsForSet().add("set","yiren");
        redisTemplate.opsForSet().add("set","tianjie");
        //存放zset
        redisTemplate.opsForZSet().add("1","tianjie",1d);
        redisTemplate.opsForZSet().add("1","yiren",1d);

    }

    /**
     * bitmaps
     * 主要结构为二进制字符串。只有0和1，用于统计日活。月活等数据，是否在线等
     * 此外也可以是布隆过滤器的一种实现方式
     * 0 1 0 0 0 0 0 0
     */
//    @Test
    public void bitmapTest(){

        //当前设置bitmap 为‘p’ -> 1110000
        redisTemplate.opsForValue().setBit("bitmaps",3L,true);
        //查看是否存在
        redisTemplate.opsForValue().getBit("bitmaps",1L);
        BitSet all = new BitSet();
        String bitmapstr = redisTemplate.opsForValue().get("bitmaps");
        System.out.println("获取bitmap字符串："+bitmapstr);
        BitSet bitmap = BitSet.valueOf(bitmapstr.getBytes());
        //任意为1 则为1
        //0000000
        //1110000  -> 1110000
//        all.or(BitSet.valueOf(bitmapstr.getBytes()));

//        //都为1则为1
        //0000000
        //1110000 -> 0000000
//        all.and(BitSet.valueOf(bitmapstr.getBytes()));

        //1 && 0 = 1
        //0000000
        //1110000 ->0000000
//        all.andNot(BitSet.valueOf(bitmapstr.getBytes()));
        //1110000
        //0000000 ->1110000
        bitmap.andNot(all);
//        System.out.println(all.cardinality());
        System.out.println(bitmap.cardinality());
    }

//    @Test
    public void hyperloglogTest(){
        for(int i=0;i<100000;i++){
            redisTemplate.opsForHyperLogLog().add("tianjie",i+"");
        }
        //输出99565 ，误差率 = 0.435%<标准0.81
        System.out.println(redisTemplate.opsForHyperLogLog().size("tianjie"));
    }

//    @Test
    public void GeoTest(){
        redisTemplate.opsForGeo().add("citys",new Point(118.801272,32.074199),"nanjing");
        redisTemplate.opsForGeo().add("citys",new Point(119.808982,33.466701),"jianhu");
        //测量距离
        double distance = redisTemplate.opsForGeo().distance("citys","nanjing","jianhu",  RedisGeoCommands.DistanceUnit.KILOMETERS).getValue();
        System.out.println(distance);
        //取半径200公里内的
        Distance dis = new Distance(200, Metrics.KILOMETERS);
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().includeCoordinates().sortAscending().limit(5);
        GeoResults<RedisGeoCommands.GeoLocation<String>> results = redisTemplate.opsForGeo()
                .radius("citys","jianhu",dis,args);
        System.out.println(results);
    }

//    @Test
    public void pubSubTest(){
        redisTemplate.convertAndSend("channel1","hello world!");
    }

    /**
     * 使用keys，设置过期时间为2秒。
     * 场景为2秒内不能访问多少次。
     * 也可以防止用户页面多次点击
     */
//    @Test
    public void frequencyTest(){

        //在百万级的应用上。容易出现问题。因为keys命令会阻塞线程,使用rename-command KEYS "" 禁用命令
//        for(int i=0;i<100;i++){
//            String userAction= "12131321Action";
//            Set<String> actions = redisTemplate.opsForValue().getOperations().keys(userAction+"*");
//            if(actions.size()>10){
//                System.out.println("您的访问频次过高，请稍后再试");
//            }else{
//                redisTemplate.opsForValue().set(userAction+ UUID.randomUUID(),"",2, TimeUnit.SECONDS);
//                System.out.println("访问成功");
//            }
//
//        }

        for(int i=0;i<100;i++){
            String userAction= "12131321Action";
            Cursor<byte[]> cursor = redisTemplate.getConnectionFactory().getConnection().scan(ScanOptions.scanOptions().count(10).match(userAction+"*").build());
            List<String> keys = new ArrayList<>();
            cursor.forEachRemaining(item-> keys.add(new String(item)));
            if(keys.size()>10){
                System.out.println("您的访问频次过高，请稍后再试");
            }else{
                System.out.println("访问成功");
                redisTemplate.opsForValue().set(userAction+ UUID.randomUUID(),"",2, TimeUnit.SECONDS);

            }

        }
    }

//    @Test
    public void primaryKeyTest() throws InterruptedException {
        ExecutorService service = new ThreadPoolExecutor(20,20,300l,TimeUnit.SECONDS,new ArrayBlockingQueue<>(20));
        CountDownLatch countDownLatch = new CountDownLatch(20);

        //模拟分布式服务同步操作
        for(int i=0;i<20;i++){
            service.execute(new FbsIdTask(redisTemplate,countDownLatch));
        }
        Thread.sleep(10000L);
    }

    class FbsIdTask implements Runnable{

        private StringRedisTemplate redisTemplate;

        private CountDownLatch countDownLatch;

        public FbsIdTask(StringRedisTemplate redisTemplate,CountDownLatch countDownLatch) {
            this.redisTemplate = redisTemplate;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            countDownLatch.countDown();
            try {
                System.out.println("阻塞，等待一起执行,"+countDownLatch.getCount());
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(int i=0;i<10;i++){
                DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                String ymd = dateFormat.format(new Date());
                //increment 天生redis 进行加锁操作
                Long key = redisTemplate.opsForValue().increment(ymd);

                System.out.println(ymd+String.format("%08d", key));
            }

        }
    }

//    @Test
    public void lockTest() throws InterruptedException {
        ExecutorService service = new ThreadPoolExecutor(5,20,300l,TimeUnit.SECONDS,new ArrayBlockingQueue<>(20));
        CountDownLatch countDownLatch = new CountDownLatch(5);

        //模拟分布式服务同步操作
        for(int i=0;i<5;i++){
            service.execute(new LockTask(redisTemplate,countDownLatch));
        }
        Thread.sleep(10000L);
    }

//    @Test
    public void TransactionlTest() throws InterruptedException {
        redisTemplate.opsForValue().getOperations().multi();
        redisTemplate.opsForValue().getOperations().exec();

    }

//    @Test
    public void streamTest()  {
        Map<String,String> usr = new HashMap<>();
        usr.put("id","1");
        usr.put("name","tinajie");
        usr.put("age","27");
        StringRecord stringRecord = StreamRecords.string(usr).withStreamKey("mystream");

        redisTemplate.opsForStream().add(stringRecord);

    }
}
