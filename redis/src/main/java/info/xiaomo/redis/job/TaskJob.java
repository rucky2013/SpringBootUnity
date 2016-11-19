package info.xiaomo.redis.job;

import com.alibaba.fastjson.JSON;
import info.xiaomo.redis.model.CityInfo;
import info.xiaomo.redis.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class TaskJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskJob.class);
    private static final List<String> list = Arrays.asList("北京市", "上海市", "天津市", "重庆市", "河北省", "山西省", "内蒙古自治区", "辽宁省",
            "吉林省", "黑龙江", "江苏省", "浙江省", "安徽省", "福建省", "江西省", "山东省", "河南省", "湖北省", "湖南省", "广东省", "广西自治区", "海南省", "四川省",
            "贵州省", "云南省", "西藏自治区", "陕西省", "甘肃省", "青海省", "宁夏自治区", "新疆自治区", "香港特别行政区", "澳门特别行政区", "台湾省");
    private final CityService cityService;

    @Autowired
    public TaskJob(CityService cityService) {
        this.cityService = cityService;
    }

    /**
     * Job
     */
    @Scheduled(fixedDelay = 500)
    public void retrieveCountry() {
        int index = new Random().nextInt(list.size());
        String city = find(index);
        CityInfo info = cityService.getCity(index, city);
        LOGGER.debug("{}", JSON.toJSONString(info));
    }

    private String find(int index) {
        return list.get(index);
    }
}