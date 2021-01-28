#!/bin/bash

EC2_INSTANCE="$1"

curl -X POST -u user:password -H 'Content-Type: application/json' -d '{"pedidoId": null, "usuarioId": 4, "estado": "PENDIENTE", "importePagoGift": 12.0, "lineasPedido": [{"lineaPedidoId": null, "productoId": 3, "precio": 3.87, "cantidad": 15}, {"lineaPedidoId": null, "productoId": 4, "precio": 0.89, "cantidad": 7}]}' http://$EC2_INSTANCE:9999/creaPedido
