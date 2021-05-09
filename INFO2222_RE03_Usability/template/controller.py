'''
    This file will handle our typical Bottle requests and responses 
    You should not have anything beyond basic page loads, handling forms and 
    maybe some simple program logic
'''

from bottle import route, get, post, error, request, static_file

from model import login_check
import model

#-----------------------------------------------------------------------------
# Static file paths
#-----------------------------------------------------------------------------

# Allow image loading
@route('/img/<picture:path>')
def serve_pictures(picture):
    '''
        serve_pictures

        Serves images from static/img/

        :: picture :: A path to the requested picture

        Returns a static file object containing the requested picture
    '''
    return static_file(picture, root='static/img/')

#-----------------------------------------------------------------------------

# Allow CSS
@route('/css/<css:path>')
def serve_css(css):
    '''
        serve_css

        Serves css from static/css/

        :: css :: A path to the requested css

        Returns a static file object containing the requested css
    '''
    return static_file(css, root='static/css/')

#-----------------------------------------------------------------------------

# Allow javascript
@route('/js/<js:path>')
def serve_js(js):
    '''
        serve_js

        Serves js from static/js/

        :: js :: A path to the requested javascript

        Returns a static file object containing the requested javascript
    '''
    return static_file(js, root='static/js/')

#-----------------------------------------------------------------------------

# Allow fonts
@route('/fonts/<fonts:path>')
def serve_js(fonts):
    '''
        serve_fonts

        Serves fonts from static/fonts/

        :: fonts :: A path to the requested javascript

        Returns a static file object containing the requested fonts
    '''
    return static_file(fonts, root='static/fonts/')

#-----------------------------------------------------------------------------
# Pages
#-----------------------------------------------------------------------------

# Redirect to login
@get('/')
@get('/home')
def get_index():
    '''
        get_index
        
        Serves the index page
    '''
    return model.login_form()

#-----------------------------------------------------------------------------

# Display the login page
@get('/login')
def get_login_controller():
    '''
        get_login
        
        Serves the login page
    '''
    return model.login_form()

#-----------------------------------------------------------------------------

# Attempt the login
@post('/login')
def post_login():
    '''
        post_login
        
        Handles login attempts
        Expects a form containing 'email' and 'password' fields
    '''

    # Handle the form processing
    email = request.forms.get('email')
    password = request.forms.get('password')
    
    # Call the appropriate method
    return model.login_check(email, password)


#-----------------------------------------------------------------------------

# Display the Signup page
@get('/signup')
def get_signup_controller():
    '''
        get_sign
        
        Serves the signup page
    '''
    return model.sign_up_form()
#-----------------------------------------------------------------------------

# Attempt the signup
@post('/signup')
def post_signup():
    '''
        post_signup
        
        Handles signup attempts
        Expects a form containing 
        'email', 'confirmpassword', 'password', 'displayname'fields
    '''

    # Handle the form processing
    email = request.forms.get('email')
    password = request.forms.get('password')
    confirmpassword = request.forms.get('confirmpassword')
    displayname = request.forms.get('displayname')
    
    # Call the appropriate method
    return model.sign_up(email, password, displayname)

#-----------------------------------------------------------------------------


@get('/logout')
def get_logout_page():
    '''
        get_logout_page
        
        Serves the logout page
    '''
    return model.log_out()

#-----------------------------------------------------------------------------

@get('/about')
def get_about():
    '''
        get_about
        
        Serves the about page
    '''
    return model.about()
#-----------------------------------------------------------------------------

#-----------------------------------------------------------------------------

@get('/account')
def get_account():
    '''
        get_account
        
        Serves the account page
    '''
    return model.account()
#-----------------------------------------------------------------------------

# Help with debugging
@post('/debug/<cmd:path>')
def post_debug(cmd):
    return model.debug(cmd)

#-----------------------------------------------------------------------------

# 404 errors, use the same trick for other types of errors
@error(404)
def error(error): 
    return model.handle_errors(error)

@get('/feed')
def feed():
    '''
        feed
        
        Serves the forum page
    '''
    return model.feed()


@get('/course')
def get_course():
    '''
        get_course
        
        Serves the course page
    '''
    if current_user:
        return model.course()
    else:
        return model.invalid()


@get('/html1')
def get_html1():
    '''
        get_html-1
        
        Serves the html page
    '''
    return model.html_1()

@get('/html2')
def get_html1():
    '''
        get_html-2
        
        Serves the html page
    '''
    return model.html_2()

@get('/bottle1')
def get_bottle1():
    '''
        get_bottle-1
        
        Serves the bottle page
    '''
    return model.bottle_1()

@get('/bottle2')
def get_bottle2():
    '''
        get_bottle-2
        
        Serves the bottle page
    '''
    return model.bottle_2()

@get('/css1')
def get_css1():
    '''
        get_css1
        
        Serves the css page
    '''
    return model.css_1()

@get('/css2')
def get_css2():
    '''
        get_css2
        
        Serves the css page
    '''
    return model.css_2()

@get('/js1')
def get_js1():
    '''
        get_js1
        
        Serves the js1 page
    '''
    return model.js_1()

@get('/js2')
def get_js2():
    '''
        get_js2
        
        Serves the js2 page
    '''
    return model.js_2()

@get('/py1')
def get_py1():
    '''
        get_py1
        
        Serves the py1 page
    '''
    return model.py_1()

@get('/py2')
def get_py2():
    '''
        get_py2
        
        Serves the py2 page
    '''
    return model.py_2()

