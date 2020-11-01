docker run -it --name mynginx2 -p 8091:8090 \
-v  $PWD/nginx.conf:/etc/nginx/conf.d/nginx.conf  \
-v $PWD/../nginx/staticweb:/usr/share/nginx/staticWeb \
 nginx:1.10-alpine
