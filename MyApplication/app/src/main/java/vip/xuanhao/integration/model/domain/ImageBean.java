package vip.xuanhao.integration.model.domain;

public class ImageBean {
        private String link; //点击地址
        private String url; //图片地址

        public ImageBean(String link, String url) {
            this.link = link;
            this.url = url;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getUrl() {
            return url;
        }


        @Override
        public String toString() {
            return "ImageBean{" +
                    "link='" + link + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }