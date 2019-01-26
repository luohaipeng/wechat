package com.petrel.service.impl;


import com.petrel.mapper.ProductMapper;
import com.petrel.mapper.ProductSkuMapper;
import com.petrel.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CarServiceImpl implements ICarService {

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Autowired
    private ProductMapper productMapper;

    /*@Autowired
    private RedisTemplate redisTemplate;*/

    /*@Override
    public void addCar(UserLogin userLogin, Long skuId, Integer number)  {

        //如果登录了，通过skuId和当前登录用户的id，去数据库查询出对应的car数据
        Car car = carMapper.findCar(userLogin.getId(),skuId);
        //给查询出来的car购买数量加上当前需要购买的数量，并更新该car
        if(car != null){
            car.setProductNumber(car.getProductNumber() + number);
            carMapper.updateByPrimaryKey(car);
        }else{
            car = new Car();
            car.setProductNumber(number);
            car.setUserId(userLogin.getId());
            car.setSkuId(skuId);
            //通过skuId获取sku对象
            ProductSku productSku = productSkuMapper.selectByPrimaryKey(skuId);
            Product product = productMapper.selectByPrimaryKey(productSku.getProductId());
            car.setProductName(product.getName());
            car.setProductImg(product.getImage());
            //如果没有查询出car，那么new一个car，插入数据库
            carMapper.insert(car);
        }
    }*/
   /* @Override
    public void addCar(UserLogin userLogin, CarVo car)  {

        *//*StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(stringRedisSerializer);*//*


        Product product = productMapper.selectByPrimaryKey(car.getProductId());
        ProductSku productSku = productSkuMapper.selectByPrimaryKey(car.getSkuId());
        //通过userID和productId和skuId拼接成一个购物车数据的key
        String carKey = RedisConstant.CAR_KEY
                .replace("USERID", userLogin.getId() + "")
                .replace("PRODUCTID", car.getProductId() + "")
                .replace("SKUID", car.getSkuId() + "");

        //去redis中查找该key对应的购物车数据
        Map carMap = redisTemplate.opsForHash().entries(carKey);

        //如果获取到的carMap不为空，说明之前该用户添加过该sku商品，那就在原来的购物车数量上加上此次的数量
        if(carMap != null && carMap.size() > 0){
            //redisTemplate.opsForHash().increment(carKey,"number",car.getNumber());
            Integer number = (Integer) redisTemplate.opsForHash().get(carKey,"number");
            redisTemplate.opsForHash().put(carKey,"number",number+car.getNumber());
            System.out.println(carMap);
        }
        //否则在redis中新增该购物车数据
        else{
            carMap = new HashMap<>();
            carMap.put("productId",car.getProductId());
            carMap.put("skuId",car.getSkuId());
            carMap.put("number",car.getNumber());
            carMap.put("price",productSku.getPrice());
            carMap.put("status",new Byte("1"));
            carMap.put("productName",product.getName());
            carMap.put("productImg",product.getImage());
            Map<String, String> skuProperty = new HashMap<>();
            for(ProductSkuProperty productSkuProperty : productSku.getProductSkuPropertyList()){
                skuProperty.put(productSkuProperty.getSkuProperty().getName(),productSkuProperty.getValue());
            }
            carMap.put("skuProperty",skuProperty);
            carMap.put("select",true);
            redisTemplate.opsForHash().putAll(carKey,carMap);
        }
    }*/

}
