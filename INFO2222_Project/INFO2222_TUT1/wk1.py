from bottle import static_file, run, route

@route('/assets/<filename:path>')
def send_static(filename):
    print(filename)
    return static_file(filename, root='./SUITS/assets/')

@route('/assets_sync/<filename:path>')
def send_static(filename):
    print(filename)
    return static_file(filename, root='./SYNCS/assets/')

@route('/')
@route('/module1/syncs')
def syncs():
    return static_file("About Us - SYNCS.html", root = './SYNCS/')

@route('/module1/suits')
def suits():
    return static_file("About Us - SUITS.html", root = './SUITS/')

run(host = 'localhost', port = 8080, debug = True)
