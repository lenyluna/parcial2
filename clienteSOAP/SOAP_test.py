#!/usr/bin/env python

from suds.client import Client
url = "http://localhost:7777/ws/AplicationWebService?wsdl"
prefix = "http://localhost:4567"
client = Client(url)
print client
Posts=client.service.getPosts()
Byuser = client.service.getPostsByUsername("zomgod")

print "***************************************"
print "Listado de Post"
print "***************************************"

for post in Posts:
    print post.titulo + "\n" + post.descripcion + "\n" + prefix + post.urlimagen + "\n  ****************************** \n"


print "***************************************"
print "Listado de Post -> Zomgod"
print "***************************************"

for post in Byuser:
    print post.titulo + "\n" + post.descripcion + "\n" + prefix + post.urlimagen + "\n  ****************************** \n"