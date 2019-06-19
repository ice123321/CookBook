package com.wei.cookbook.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;


public class FoodTypeBean
{

    private String parentId;
    private String name;
    private List<ListBean> list;

    public String getParentId()
    {
        return parentId;
    }

    public void setParentId(String parentId)
    {
        this.parentId = parentId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<ListBean> getList()
    {
        return list;
    }

    public void setList(List<ListBean> list)
    {
        this.list = list;
    }

    public static class ListBean implements Parcelable
    {


        private int id;
        private String name;
        private String parentId;


        protected ListBean(Parcel in)
        {
            id = in.readInt();
            name = in.readString();
            parentId = in.readString();
        }

        public static final Creator<ListBean> CREATOR = new Creator<ListBean>()
        {
            @Override
            public ListBean createFromParcel(Parcel in)
            {
                return new ListBean(in);
            }

            @Override
            public ListBean[] newArray(int size)
            {
                return new ListBean[size];
            }
        };

        public int getId()
        {
            return id;
        }

        public void setId(int id)
        {
            this.id = id;
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public String getParentId()
        {
            return parentId;
        }

        public void setParentId(String parentId)
        {
            this.parentId = parentId;
        }

        @Override
        public int describeContents()
        {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags)
        {
            dest.writeInt(id);
            dest.writeString(name);
            dest.writeString(parentId);
        }
    }
}
