from flask_cors import CORS
from flask import Flask, request, send_from_directory
app = Flask(__name__)
CORS(app)
import requests

@app.route('/')
def indexPage():
    return send_from_directory('static','landingPage.html')


def getYelpReview(keyWord,distance,category,locationLat,locationLong):
    return requests.get('https://api.yelp.com/v3/businesses/search?term='+keyWord+'&latitude='+str(locationLat)+'&longitude='+str(locationLong)+'&radius='+str(distance)+'&categories='+category,headers={"Authorization": "Bearer "+'gTP5oJqYkgE1RTPaJhYsV7lJ_tDaBlTZLiQ4iHP-5Jk7o5SFvtQGT7UYvV3M9kb0L-pVamtGIF5bPQn54Qim24ZNBfrVEgzp2ltLERKfsJJ2kMZutKD47yJpGZk7Y3Yx'}).json()

def gbd(id):
    headers = {"Authorization": "Bearer "+"gTP5oJqYkgE1RTPaJhYsV7lJ_tDaBlTZLiQ4iHP-5Jk7o5SFvtQGT7UYvV3M9kb0L-pVamtGIF5bPQn54Qim24ZNBfrVEgzp2ltLERKfsJJ2kMZutKD47yJpGZk7Y3Yx"}
    return requests.get('https://api.yelp.com/v3/businesses/'+id,headers=headers).json()


@app.route('/getDets',methods=['GET'])
def getDets():
    return getYelpReview(request.args.get('keyWord'),request.args.get('distance'),request.args.get('category'),request.args.get('locationLat'),request.args.get('locationLong'))

@app.route('/getBusinessDets',methods=['GET'])
def getBusinessDets():
    return gbd(request.args.get('id'))

if __name__ == '__app__':
    app.run()
