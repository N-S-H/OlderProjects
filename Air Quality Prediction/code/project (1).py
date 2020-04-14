# -*- coding: utf-8 -*-
"""
Created on Fri Nov 22 14:13:44 2019

@author: SAISHRAWAN VUTHARKAR
"""

import numpy as np 
import pandas as pd 
import seaborn as sns
import matplotlib.pyplot as plt
#matplotlib inline
data = pd.read_csv('data.csv',encoding='unicode_escape') #import data
data.head(10) #print first 10 rows
data.tail(10) #printing last 10 rows
data.columns #print the columns/features of the data
data.describe() #basic info of the dataset
data.shape #dimensions of the data
data[['so2','state']].groupby(["state"]).mean().sort_values(by='so2').head(20).plot.bar(color='b')
plt.show()
data[['no2','state']].groupby(["state"]).mean().sort_values(by='no2').plot.bar(color='g')
plt.show()
data[['spm','state']].groupby(["state"]).mean().sort_values(by='spm').plot.bar(color='b')
plt.show()
data[['rspm','state']].groupby(["state"]).mean().sort_values(by='rspm').plot.bar(color='r')
plt.show()
data.isna().sum() #print the sum of null values for each columns
data.drop(['stn_code','agency','sampling_date','location_monitoring_station'],axis=1,inplace=True)
data.head(10)
total = data.isnull().sum().sort_values(ascending=False)

total.head()
percent = (data.isnull().sum()/data.isnull().count()*100).sort_values(ascending=False) 
missing_data = pd.concat([total, percent], axis=1, keys=['Total', 'Percent'])

missing_data.head()
sns.barplot(x=missing_data.index, y=missing_data['Percent'])
plt.xlabel('Features', fontsize=20)
plt.ylabel('Percent of missing values', fontsize=20)
plt.title('Percent missing data by feature', fontsize=20)
data.groupby('state')[['spm','pm2_5','rspm','so2','no2']].mean()

plt.hist(data.spm,range=(0.0,4000)) #spm
plt.hist(data.so2,range=(0,1000)) #so2
plt.hist(data.no2,range=(0,1000)) #no2

plt.hist(data.rspm,range=(0,7000)) #rspm
plt.hist(data.pm2_5,range=(0,1000)) #pm2_5
grp_state = data.groupby('state')
def impute_mean_by_state(series):
    return series.fillna(series.mean())

data['rspm']=grp_state['rspm'].transform(impute_mean_by_state)  #fill value with mean value group by state
data['so2']=grp_state['so2'].transform(impute_mean_by_state)
data['no2']=grp_state['no2'].transform(impute_mean_by_state)
data['spm']=grp_state['spm'].transform(impute_mean_by_state)
data['pm2_5']=grp_state['pm2_5'].transform(impute_mean_by_state)

data.describe()

data.isna().sum() #some null value remains since some state have one value(i.e NaN only) and no mean to replace them

plt.hist(data.so2,range=(0,1000))

plt.hist(data.rspm,range=(0,7000))

plt.hist(data.no2,range=(0.0,1000))
plt.hist(data.spm,range=(0.0,4000)) #spm


data.tail(10)

def cal_SOi(so2):
    si=0
    if (so2<=40):
     si= so2*(50/40)
    elif (so2>40 and so2<=80):
     si= 50+(so2-40)*(50/40)
    elif (so2>80 and so2<=380):
     si= 100+(so2-80)*(100/300)
    elif (so2>380 and so2<=800):
     si= 200+(so2-380)*(100/420)
    elif (so2>800 and so2<=1600):
     si= 300+(so2-800)*(100/800)
    elif (so2>1600):
     si= 400+(so2-1600)*(100/800)
    return si
data['SOi']=data['so2'].apply(cal_SOi)
df= data[['so2','SOi']]
df.head()


def cal_Noi(no2):
    ni=0
    if(no2<=40):
     ni= no2*50/40
    elif(no2>40 and no2<=80):
     ni= 50+(no2-40)*(50/40)
    elif(no2>80 and no2<=180):
     ni= 100+(no2-80)*(100/100)
    elif(no2>180 and no2<=280):
     ni= 200+(no2-180)*(100/100)
    elif(no2>280 and no2<=400):
     ni= 300+(no2-280)*(100/120)
    else:
     ni= 400+(no2-400)*(100/120)
    return ni
data['Noi']=data['no2'].apply(cal_Noi)
df= data[['no2','Noi']]
df.head()

def cal_RSPMi(rspm):
    rpi=0
    if(rspm<=100):
     rpi = rspm
    elif(rspm>=101 and rspm<=150):
     rpi= 101+(rspm-101)*((200-101)/(150-101))
    elif(rspm>=151 and rspm<=350):
     ni= 201+(rspm-151)*((300-201)/(350-151))
    elif(rspm>=351 and rspm<=420):
     ni= 301+(rspm-351)*((400-301)/(420-351))
    elif(rspm>420):
     ni= 401+(rspm-420)*((500-401)/(420-351))
    return rpi
data['RSPMi']=data['rspm'].apply(cal_RSPMi)
df= data[['rspm','RSPMi']]
df.head()

def cal_SPMi(spm):
    spi=0
    if(spm<=50):
     spi=spm*50/50
    elif(spm>50 and spm<=100):
     spi=50+(spm-50)*(50/50)
    elif(spm>100 and spm<=250):
     spi= 100+(spm-100)*(100/150)
    elif(spm>250 and spm<=350):
     spi=200+(spm-250)*(100/100)
    elif(spm>350 and spm<=430):
     spi=300+(spm-350)*(100/80)
    else:
     spi=400+(spm-430)*(100/430)
    return spi
   
data['SPMi']=data['spm'].apply(cal_SPMi)
df= data[['spm','SPMi']]
df.head()

def cal_pmi(pm2_5):
    pmi=0
    if(pm2_5<=50):
     pmi=pm2_5*(50/50)
    elif(pm2_5>50 and pm2_5<=100):
     pmi=50+(pm2_5-50)*(50/50)
    elif(pm2_5>100 and pm2_5<=250):
     pmi= 100+(pm2_5-100)*(100/150)
    elif(pm2_5>250 and pm2_5<=350):
     pmi=200+(pm2_5-250)*(100/100)
    elif(pm2_5>350 and pm2_5<=450):
     pmi=300+(pm2_5-350)*(100/100)
    else:
     pmi=400+(pm2_5-430)*(100/80)
    return pmi
data['PMi']=data['pm2_5'].apply(cal_pmi)
df= data[['pm2_5','PMi']]
df.head()

type(data['PMi'])

def cal_aqi(si,ni,rspmi,spmi):
    aqi=0
    if(si>ni and si>rspmi and si>spmi):
     aqi=si
    if(ni>si and ni>rspmi and ni>spmi ):
     aqi=ni
    if(rspmi>si and rspmi>ni and rspmi>spmi ):
     aqi=rspmi
    if(spmi>si and spmi>ni and spmi>rspmi):
     aqi=spmi
    return aqi

data['AQI']=data.apply(lambda x:cal_aqi(x['SOi'],x['Noi'],x['RSPMi'],x['SPMi']),axis=1)
df= data[['state','SOi','Noi','RSPMi','SPMi','AQI']]
df.head()

data.head()

def AQI_Range(x):
    if x<=50:
        return "Good"
    elif x>50 and x<=100:
        return "Moderate"
    elif x>100 and x<=200:
        return "Poor"
    elif x>200 and x<=300:
        return "Unhealthy"
    elif x>300 and x<=400:
        return "Very unhealthy"
    elif x>400:
        return "Hazardous"

data['AQI_Range'] = data['AQI'] .apply(AQI_Range)
data.head()

d=data #saving data in new value
d.head()

data=data.dropna(subset=['spm']) #spm

data=data.dropna(subset=['pm2_5']) #spm

data.isna().sum() #all null values removed




from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
from sklearn.metrics import mean_squared_log_error
from sklearn.metrics import mean_squared_error
from sklearn.metrics import r2_score, mean_squared_error
from sklearn.model_selection import KFold
from sklearn.model_selection import cross_val_score

"""
KNN
"""
X0 = data[['SOi','Noi','RSPMi','SPMi']]
y0 = data['AQI']
y0.head()

X0_train, X0_test, y0_train, y0_test = train_test_split(X0,y0, test_size=0.2,random_state=101)


from sklearn.neighbors import KNeighborsClassifier
from sklearn import preprocessing
from sklearn import utils
lab_enc = preprocessing.LabelEncoder()
y_encoded = lab_enc.fit_transform(y0_train)
knn = KNeighborsClassifier(n_neighbors=3,leaf_size=30)
knn.fit(X0_train,y_encoded)
y0_pred = knn.predict(X0_test)
predictions0=knn.predict(X0_test)
print('R^2_Square:%.2f '% r2_score(y0_test, predictions0))
print('MSE:%.2f '% np.sqrt(mean_squared_error(y0_test, predictions0)))

#print(metrics.accuracy_score(y0_test, y0_pred))
"""
XX0 = data[['SOi','Noi','RSPMi','SPMi']]
yy0 = data['AQI']
XX0_train, XX0_test, yy0_train, yy0_test = train_test_split(XX0,yy0, test_size=0.2,random_state=101)
from sklearn.svm import SVC
from sklearn import preprocessing
from sklearn import utils
lab_enc = preprocessing.LabelEncoder()
yy_encoded = lab_enc.fit_transform(yy0_train)
svclassifier = SVC(kernel='polynomial')
svclassifier.fit(XX0_train, yy_encoded)
yy0_pred = svclassifier.predict(XX0_test)
predictions00=svclassifier.predict(XX0_test)
print('R^2_Square:%.2f '% r2_score(yy0_test, predictions00))
print('MSE:%.2f '% np.sqrt(mean_squared_error(yy0_test, predictions00)))

"""
"""
X = data[['SOi','Noi','RSPMi','SPMi']]
y = data['AQI']
y.head()
X = X.apply(pd.to_numeric, errors='coerce')
Y = Y.apply(pd.to_numeric, errors='coerce')

X_train, X_test, y_train, y_test = train_test_split(X,y, test_size=0.2,random_state=101)

X_train.head()

#print(metrics.accuracy_score(y_train, y_pred))

LR = LinearRegression() 
LR.fit(X_train, y_train)

print('Intercept',LR.intercept_)

print('Coefficients',LR.coef_)

predictions = LR.predict(X_test)


plt.scatter(y_test,predictions)
plt.xlabel('Y Test')
plt.ylabel('Predicted Y')

LR.score(X_test,y_test)
LR.predict([[4.8,21.75,78.18,100]])
LR.predict([[5.2,7.625,76.53,75.0]])
sns.regplot(y_test,predictions)
print('R^2_Square:%.2f '% r2_score(y_test, predictions))
print('MSE:%.2f '% np.sqrt(mean_squared_error(y_test, predictions)))
"""
X = data[['SOi','Noi','RSPMi','SPMi']]
y = data['AQI']
y.head()


X_train, X_test, y_train, y_test = train_test_split(X,y, test_size=0.2,random_state=101)

X_train.head()

LR = LinearRegression()
LR.fit(X_train, y_train)

prediction = LR.predict(X_test)

plt.scatter(y_test,prediction) #scatter plot for actual and predicted values
plt.xlabel('Y Test')
plt.ylabel('Predicted Y')

LR.predict([[9.1,16.3,67,179]])
sns.regplot(y_test,prediction) #regression plot

y_test_np= np.array(y_test)
prediction_np = np.array(prediction)

LR.score(X_test,y_test) #accuracy score 76.82%

print('R^2_Square:%.2f '% r2_score(y_test, prediction))
print('MSE:%.2f '% np.sqrt(mean_squared_error(y_test, prediction)))



X1= data[['so2','no2','rspm','spm']]
y1 = data['AQI']
y.tail()


X_train1, X_test1, y_train1, y_test1 = train_test_split(X1,y1, test_size=0.2,random_state=101)

X_train1.head()

LR1 = LinearRegression()
LR1.fit(X_train1, y_train1)

prediction1 = LR1.predict(X_test1)

plt.scatter(y_test1,prediction1) #scatter plot for actual and predicted values
plt.xlabel('Y Test')
plt.ylabel('Predicted Y')

LR1.predict([[9.1,16.3,67,179]])
sns.regplot(y_test1,prediction1) #regression plot

y_test1_np= np.array(y_test1)
prediction1_np = np.array(prediction1)

LR1.score(X_test1,y_test1) #accuracy score 76.82%

print('R^2_Square:%.2f '% r2_score(y_test1, prediction1))
print('MSE:%.2f '% np.sqrt(mean_squared_error(y_test1, prediction1)))

from sklearn.linear_model import LogisticRegression

X2 = data[['SOi','Noi','RSPMi','SPMi']]
y2 = data['AQI_Range']

X_train2, X_test2, y_train2, y_test2 = train_test_split(X2, y2, test_size=0.33, random_state=42)

logmodel = LogisticRegression()
logmodel.fit(X_train2,y_train2)

predictions = logmodel.predict(X_test)

logmodel.score(X_test2,y_test2) #accuracy score 89.25 %

new = pd.DataFrame(d)
file1 = 'new1.csv'
new.to_csv(file1,index=True)


d.tail()

logmodel.predict([[77.4,147.7,78.182,100]]) #correct
logmodel.predict([[32.7,35,78.182,203]]) #correct
logmodel.predict([[100,182.2,78.182,400]]) #correct

X3 = data[['so2','no2','rspm','spm']]
y3 = data['AQI_Range']
X_train3, X_test3, y_train3, y_test3 = train_test_split(X3, y3, test_size=0.33, random_state=42)
logmodel2 = LogisticRegression()
logmodel2.fit(X_train3,y_train3)
logmodel2.score(X_test3,y_test3) #very low accuracy score
logmodel2.predict([[4.8,17.4,78.48,200]]) #correct
logmodel2.predict([[67.4,127.7,78.48,215]]) #correct
logmodel2.predict([[2.059,8.94,102,256]])  #wrong
from sklearn.ensemble import RandomForestClassifier
model = RandomForestClassifier(n_estimators=10)
model.fit(X_train3,y_train3)
model.score(X_test3,y_test3) #high accuracy score of 99.97 %
X_train3.head()
model.predict([[2.059,8.94,102,256]]) #correct
from sklearn import tree
model2 = tree.DecisionTreeClassifier()
model2.fit(X_train3,y_train3)
model2.score(X_test3,y_test3) #high accuracy score of 99.98%
model2.predict([[9,31,51,205.25]]) # correct
model2.predict([[2,5.8,17,36]]) # correct
model2.predict([[18.6,48.3,142,285]]) # correct
model2.predict([[6,11,109,84.41]]) # correct
model2.predict([[10,16,156,372.66]]) # correct