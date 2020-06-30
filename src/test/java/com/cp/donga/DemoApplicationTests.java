package com.cp.donga;

import com.cp.donga.domain.Donga;
import com.cp.donga.repository.DongaRepository;
import com.cp.donga.repository.SceneRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private SceneRepository repo;
	@Autowired
	private DongaRepository repod;

	@Test
	public void updateTest(){
		String json = "{\"objects\": [{\"src\": \"http://192.168.0.34:8080/pictures/53/IMG_0066.jpg\", \"top\": 150, \"fill\": \"rgb(0,0,0)\", \"left\": 310, \"type\": \"image\", \"angle\": 0, \"cropX\": 0, \"cropY\": 0, \"flipX\": false, \"flipY\": false, \"skewX\": 0, \"skewY\": 0, \"width\": 3024, \"clipTo\": null, \"height\": 4032, \"scaleX\": 0.09, \"scaleY\": 0.09, \"shadow\": null, \"stroke\": null, \"filters\": [], \"opacity\": 1, \"originX\": \"left\", \"originY\": \"top\", \"version\": \"3.6.1\", \"visible\": true, \"fillRule\": \"nonzero\", \"paintFirst\": \"fill\", \"crossOrigin\": \"\", \"strokeWidth\": 0, \"strokeLineCap\": \"butt\", \"strokeLineJoin\": \"miter\", \"backgroundColor\": \"\", \"strokeDashArray\": null, \"transformMatrix\": null, \"strokeDashOffset\": 0, \"strokeMiterLimit\": 4, \"globalCompositeOperation\": \"source-over\"}], \"version\": \"3.6.1\", \"background\": \"rgba(255, 242, 204, 0.25)\", \"backgroundImage\": {\"src\": \"http://192.168.0.34:8080/img/sunAll4.png\", \"top\": 0, \"fill\": \"rgb(0,0,0)\", \"left\": 0, \"type\": \"image\", \"angle\": 0, \"cropX\": 0, \"cropY\": 0, \"flipX\": false, \"flipY\": false, \"skewX\": 0, \"skewY\": 0, \"width\": 2121, \"clipTo\": null, \"height\": 1126, \"scaleX\": 0.59, \"scaleY\": 0.59, \"shadow\": null, \"stroke\": null, \"filters\": [], \"opacity\": 1, \"originX\": \"left\", \"originY\": \"top\", \"version\": \"3.6.1\", \"visible\": true, \"fillRule\": \"nonzero\", \"paintFirst\": \"fill\", \"crossOrigin\": \"\", \"strokeWidth\": 0, \"strokeLineCap\": \"butt\", \"strokeLineJoin\": \"miter\", \"backgroundColor\": \"\", \"strokeDashArray\": null, \"transformMatrix\": null, \"strokeDashOffset\": 0, \"strokeMiterLimit\": 4, \"globalCompositeOperation\": \"source-over\"}}";
		repo.updateScene(json,Donga.builder().dongaid(53L).build(), 1L);
	}


	@Test
	void contextLoads() {
	}

}
