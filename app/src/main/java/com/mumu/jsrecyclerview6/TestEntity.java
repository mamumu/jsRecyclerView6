package com.mumu.jsrecyclerview6;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author : zlf
 * date    : 2019/4/16
 * github  : https://github.com/mamumu
 * blog    : https://www.jianshu.com/u/281e9668a5a6
 */
public class TestEntity implements Serializable {

    /**
     * Result : [{"title1":"我有一只小狗1","title2":"我有一只小狗2","list":[{"message1":"我有一只小狗我有一只小狗我有一只小狗我有一只小狗1","message2":"我有一只小狗我有一只小狗我有一只小狗我有一只小狗2"},{"message1":"我有一只小狗我有一只小狗我有一只小狗我有一只小狗1","message2":"我有一只小狗我有一只小狗我有一只小狗我有一只小狗2"}]},{"title1":"我有一只小狗1","title2":"我有一只小狗2","list":[{"message1":"我有一只小狗我有一只小狗我有一只小狗我有一只小狗1","message2":"我有一只小狗我有一只小狗我有一只小狗我有一只小狗2"},{"message1":"我有一只小狗我有一只小狗我有一只小狗我有一只小狗1","message2":"我有一只小狗我有一只小狗我有一只小狗我有一只小狗2"}]}]
     * Success : true
     * StatusCode : 200
     */

    private boolean Success;
    private int StatusCode;
    private List<ResultBean> Result;

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int StatusCode) {
        this.StatusCode = StatusCode;
    }

    public List<ResultBean> getResult() {
        return Result;
    }

    public void setResult(List<ResultBean> Result) {
        this.Result = Result;
    }

    public static class ResultBean extends AbstractExpandableItem<ResultBean.ListBean> implements MultiItemEntity {
        /**
         * title1 : 我有一只小狗1
         * title2 : 我有一只小狗2
         * list : [{"message1":"我有一只小狗我有一只小狗我有一只小狗我有一只小狗1","message2":"我有一只小狗我有一只小狗我有一只小狗我有一只小狗2"},{"message1":"我有一只小狗我有一只小狗我有一只小狗我有一只小狗1","message2":"我有一只小狗我有一只小狗我有一只小狗我有一只小狗2"}]
         */

        private String title1;
        private String title2;
        private List<ListBean> list;

        public String getTitle1() {
            return title1;
        }

        public void setTitle1(String title1) {
            this.title1 = title1;
        }

        public String getTitle2() {
            return title2;
        }

        public void setTitle2(String title2) {
            this.title2 = title2;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        @Override
        public int getLevel() {
            return 0;
        }

        @Override
        public int getItemType() {
            return TestAdapter.TYPE_LEVEL_0;
        }

        @Override
        public void setSubItems(List<ListBean> list) {
            super.setSubItems(list);
        }

        public static class ListBean implements MultiItemEntity {
            /**
             * message1 : 我有一只小狗我有一只小狗我有一只小狗我有一只小狗1
             * message2 : 我有一只小狗我有一只小狗我有一只小狗我有一只小狗2
             */

            private String message1;
            private String message2;

            public String getMessage1() {
                return message1;
            }

            public void setMessage1(String message1) {
                this.message1 = message1;
            }

            public String getMessage2() {
                return message2;
            }

            public void setMessage2(String message2) {
                this.message2 = message2;
            }

            @Override
            public int getItemType() {
                return TestAdapter.TYPE_LEVEL_1;
            }
        }
    }
}
