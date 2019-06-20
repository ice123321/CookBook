package com.wei.cookbook.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.wei.cookbook.App;
import com.wei.cookbook.sql.FoodBeanDao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;


@Entity
public class FoodBean implements Parcelable
{


    private String id;
    @Id
    private String title;
    private String tags;
    private String imtro;
    private String ingredients;
    private String burden;
    @Transient
    private List<String> albums;
    @Transient
    private List<StepsBean> steps;
    private String imageUrls;


    @Generated(hash = 1875402827)
    public FoodBean(String id, String title, String tags, String imtro, String ingredients, String burden, String imageUrls)
    {
        this.id = id;
        this.title = title;
        this.tags = tags;
        this.imtro = imtro;
        this.ingredients = ingredients;
        this.burden = burden;
        this.imageUrls = imageUrls;
    }


    @Generated(hash = 895705851)
    public FoodBean()
    {
    }


    protected FoodBean(Parcel in)
    {
        id = in.readString();
        title = in.readString();
        tags = in.readString();
        imtro = in.readString();
        ingredients = in.readString();
        burden = in.readString();
        albums = in.createStringArrayList();
        steps = in.createTypedArrayList(StepsBean.CREATOR);
        imageUrls = in.readString();
    }

    public static final Creator<FoodBean> CREATOR = new Creator<FoodBean>()
    {
        @Override
        public FoodBean createFromParcel(Parcel in)
        {
            return new FoodBean(in);
        }

        @Override
        public FoodBean[] newArray(int size)
        {
            return new FoodBean[size];
        }
    };

    public String getId()
    {
        return this.id;
    }


    public void setId(String id)
    {
        this.id = id;
    }


    public String getTitle()
    {
        return this.title;
    }


    public void setTitle(String title)
    {
        this.title = title;
    }


    public String getTags()
    {
        return this.tags;
    }


    public void setTags(String tags)
    {
        this.tags = tags;
    }


    public String getImtro()
    {
        return this.imtro;
    }


    public void setImtro(String imtro)
    {
        this.imtro = imtro;
    }


    public String getIngredients()
    {
        return this.ingredients;
    }


    public void setIngredients(String ingredients)
    {
        this.ingredients = ingredients;
    }


    public String getBurden()
    {
        return this.burden;
    }


    public void setBurden(String burden)
    {
        this.burden = burden;
    }

    public List<String> getAlbums()
    {
        return albums;
    }

    public void setAlbums(List<String> albums)
    {
        this.albums = albums;
    }

    public List<StepsBean> getSteps()
    {
        return steps;
    }

    public void setSteps(List<StepsBean> steps)
    {
        this.steps = steps;
    }

    public String getImageUrls()
    {
        return this.imageUrls;
    }


    public void setImageUrls(String imageUrls)
    {
        this.imageUrls = imageUrls;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(tags);
        dest.writeString(imtro);
        dest.writeString(ingredients);
        dest.writeString(burden);
        dest.writeStringList(albums);
        dest.writeTypedList(steps);
        dest.writeString(imageUrls);
    }


    public static class StepsBean implements Parcelable
    {

        private String img;
        private String step;

        protected StepsBean(Parcel in)
        {
            img = in.readString();
            step = in.readString();
        }

        public static final Creator<StepsBean> CREATOR = new Creator<StepsBean>()
        {
            @Override
            public StepsBean createFromParcel(Parcel in)
            {
                return new StepsBean(in);
            }

            @Override
            public StepsBean[] newArray(int size)
            {
                return new StepsBean[size];
            }
        };

        public String getImg()
        {
            return img;
        }

        public void setImg(String img)
        {
            this.img = img;
        }

        public String getStep()
        {
            return step;
        }

        public void setStep(String step)
        {
            this.step = step;
        }

        @Override
        public int describeContents()
        {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags)
        {
            dest.writeString(img);
            dest.writeString(step);
        }
    }


    public boolean isCollected()
    {
        FoodBeanDao dao = App.mSession.getFoodBeanDao();
        FoodBean data = dao.queryBuilder().where(FoodBeanDao.Properties.Id.eq(this.id)).unique();
        return data != null;
    }


    public void collect()
    {
        FoodBeanDao dao = App.mSession.getFoodBeanDao();
        this.imageUrls = (this.albums != null && this.albums.size() != 0) ? this.albums.get(0) : "";
        dao.insertOrReplace(this);
    }


    public void cancel()
    {
        FoodBeanDao dao = App.mSession.getFoodBeanDao();
        dao.delete(this);
    }
}
