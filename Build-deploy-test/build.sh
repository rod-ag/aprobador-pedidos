#!/bin/bash

#############################################
#                                           #
#   build.sh: Build a list of services      #
#                                           #
#   Parameters: List of services to build   #
#                                           #
#############################################


image_name()
{
    SERVICE_NAME="$1"

    case "$SERVICE_NAME" in
        ConfigServer)
            IMAGE_NAME="config-server"
            ;;
        
        EurekaServer)
            IMAGE_NAME="eureka-server"
            ;;
        
        ZuulProxy)
            IMAGE_NAME="zuul-proxy"
            ;;
        
        pedidos)
            IMAGE_NAME="pedidos"
            ;;
        
        productos)
            IMAGE_NAME="productos"
            ;;
        
        usuarios)
            IMAGE_NAME="usuarios"
            ;;
        
        *)
            IMAGE_NAME=""
            ;;
    esac
    
    echo "$IMAGE_NAME"
}

WORKSPACE=~/ws4-pedidos-productos-usuarios
cd $WORKSPACE

for SERVICE in "$@"
do
    IMAGE=$(image_name "$SERVICE")
    
    if [ -n "$IMAGE" ]; then
        cd $SERVICE
        ./gradlew bootJar
        cp ./build/libs/$SERVICE-0.0.1-SNAPSHOT.jar ./dockerContext
        cd dockerContext
        
        docker build -t rodag15/$IMAGE .
        docker push rodag15/$IMAGE:latest
        
        cd $WORKSPACE
    fi
done

