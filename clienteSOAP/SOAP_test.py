#!/usr/bin/env python

from suds.client import Client
# import the library
from appJar import gui
url = "http://localhost:7777/ws/AplicationWebService?wsdl"
prefix = "http://localhost:4567"
client = Client(url)
print client
Posts=client.service.getPosts()
Byuser = client.service.getPostsByUsername("zomgod")

# handle button events
def press(button):
    if button == "Cancel":
        app.stop()
    else:
        titulo = app.getEntry("Titulo")
        descri = app.getEntry("Descripcion")
        url = app.getEntry("URL Imagen")
        client.service.crear_post(titulo,descri,url)
       # print("User:", usr, "Pass:", pwd)

app = gui("Crear post", "400x200")
app.setBg("orange")
app.setFont(18)

# add & configure widgets - widgets get a name, to help referencing them later
app.addLabel("title", "PICABU for UNIX :D")
app.setLabelBg("title", "blue")
app.setLabelFg("title", "orange")

app.addLabelEntry("Titulo")
app.addLabelEntry("Descripcion")
app.addLabelEntry("URL Imagen")

# link the buttons to the function called press
app.addButtons(["Postear", "Cancel"], press)

app.setFocus("Titulo")
# start the GUI
app.go()


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

print "***************************************"
print "Subir Imagen"
print "***************************************"
