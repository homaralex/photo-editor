package projekt_android.photoeditor;

import android.content.Context;
import android.test.InstrumentationTestCase;
import android.test.RenamingDelegatingContext;

import java.util.ArrayList;
import java.util.List;

import projekt_android.photoeditor.database.ImageDataSource;

/**
 * Created by Piotrek on 2014-12-18.
 */
public abstract class ImageDataSourceTest extends InstrumentationTestCase {

    private static final String IMAGE1 = "aaaaa/bbbbbb/img1.jpg";

    private static final String IMAGE2 = "aaaa/bbb/img2.jpg";

    private static final List<String> imageList = new ArrayList<String>();

    private ImageDataSource source;

    public void setDataSource(ImageDataSource source) {
        this.source = source;
    }

    public abstract void setNewDataSource(Context context);

    @Override
    public void setUp() throws Exception {
        super.setUp();
        RenamingDelegatingContext context = new RenamingDelegatingContext(getInstrumentation().getContext(), "_test");
        setNewDataSource(context);
        source.open();
        imageList.add(IMAGE1);
        imageList.add(IMAGE2);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        source.close();
    }
/*
    public void testAddImage() {
        source.addImage(IMAGE1);
    }

    public void testGetAllUrls() {
        source.addImage(IMAGE1);
        source.addImage(IMAGE2);
        List<String> urls = source.getAllUrls();
        assertEquals("Databse sould return right number of objectss", imageList.size(), urls.size());
        for(String s : imageList){
            assertTrue("Database should contain all files", urls.contains(s));
        }
    }*/

}
