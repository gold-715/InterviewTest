package com.gold.InterviewTest;

import com.gold.InterviewTest.model.IDataCallback;
import com.gold.InterviewTest.model.PSIModel;
import com.gold.InterviewTest.model.data.PSIData;

import junit.framework.Assert;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class PSIModelUnitTest {
    final CountDownLatch signal = new CountDownLatch(1);
    PSIModel model = new PSIModel();

    @Test
    public void testGetPSIData() throws Exception {
        model.getPSI(new IDataCallback<PSIData>() {
            @Override
            public void success(PSIData data) {
                testReadingItems(data);
                signal.countDown();
            }

            @Override
            public void error(Exception error) {
                Assert.assertNull(error);
                signal.countDown();
            }
        });
        signal.await();
    }

    @Test
    public void testGetPSIByDateTime() throws Exception {
        model.getPSIByDate("2018-04-01", new IDataCallback<PSIData>() {
            @Override
            public void success(PSIData data) {
                testReadingItems(data);
                signal.countDown();
            }

            @Override
            public void error(Exception error) {
                Assert.assertNull(error);
                signal.countDown();
            }
        });
        signal.await();
    }

    private void testReadingItems(PSIData data) {
        Assert.assertNotNull(data.getRegionMetaDatas());
        Assert.assertTrue(data.getRegionMetaDatas().size() > 0);
        Assert.assertNotNull(data.getItems());
        Assert.assertNotNull(data.getApiInfo());

        for (int i = 0; i < data.getRegionMetaDatas().size(); i++) {
            Assert.assertNotNull(data.getRegionMetaDatas().get(i).getLocation());
            Assert.assertNotNull(data.getRegionMetaDatas().get(i).getLocation().getLatitude());
            Assert.assertNotNull(data.getRegionMetaDatas().get(i).getLocation().getLongitude());
            Assert.assertNotNull(data.getRegionMetaDatas().get(i).getName());
            String name = data.getRegionMetaDatas().get(i).getName();
            for(int j=0;j<data.getItems().size();j++){
                Assert.assertNotNull(data.getItems().get(j).getTimestamp());
                Assert.assertNotNull(data.getItems().get(j).getUpdateTimestamp());
                Assert.assertNotNull(data.getItems().get(j).getReadings());
                Assert.assertNotNull(data.getItems().get(j).getReadings().getCoEightHourMax().getReadingItemByPath(name));
                Assert.assertNotNull(data.getItems().get(j).getReadings().getCoSubIndex().getReadingItemByPath(name));
                Assert.assertNotNull(data.getItems().get(j).getReadings().getNo2OneHourMax().getReadingItemByPath(name));
                Assert.assertNotNull(data.getItems().get(j).getReadings().getO3EightHourMax().getReadingItemByPath(name));
                Assert.assertNotNull(data.getItems().get(j).getReadings().getO3SubIndex().getReadingItemByPath(name));
                Assert.assertNotNull(data.getItems().get(j).getReadings().getPm10SubIndex().getReadingItemByPath(name));
                Assert.assertNotNull(data.getItems().get(j).getReadings().getPm25SubIndex().getReadingItemByPath(name));
                Assert.assertNotNull(data.getItems().get(j).getReadings().getPm25TwentyFourHourly().getReadingItemByPath(name));
                Assert.assertNotNull(data.getItems().get(j).getReadings().getPsiTwentyFourHourly().getReadingItemByPath(name));
                Assert.assertNotNull(data.getItems().get(j).getReadings().getSo2SubIndex().getReadingItemByPath(name));
                Assert.assertNotNull(data.getItems().get(j).getReadings().getSo2TwentyFourHourly().getReadingItemByPath(name));
                System.out.println(name + ":" + data.getItems().get(j).getReadings().getPsiTwentyFourHourly().getReadingItemByPath(name));
            }
        }

    }

}
