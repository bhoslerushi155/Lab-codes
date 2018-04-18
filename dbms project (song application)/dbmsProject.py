from tkinter import *
from tkinter import messagebox
from mysql import connector
LogINID=2
db=connector.connect(host='localhost' , user='root' , password='root' , database='dbmsProject')
cursor=db.cursor()

def login():
    root = Tk()
    root.minsize(500, 300)
    root.maxsize(500, 300)
    root.title("Log In")


    def adminLogin():
        root = Tk()
        root.title("Admin")
        root.minsize(500, 300)
        root.maxsize(500, 300)

        def addSong():
            root = Tk()
            root.title("Add Song")
            root.minsize(500, 320)
            root.maxsize(500, 320)

            def Add():
            	par1=int(e1.get())
            	par2=e2.get()
            	par3=e3.get()
            	par4=e4.get()
            	par5=e5.get()
            	sql="insert into Songs values('%d','%s','%s','%s','%s')"%(par1,par2,par3,par4,par5)
            	cursor.execute(sql)
            	db.commit()
            	messagebox.showinfo("Success","Song added successfully...!!!")

            l1=Label(root,text="Song Id")
            e1=Entry(root,bd=5)
            l2=Label(root,text="Name")
            e2=Entry(root,bd=5)
            l3 = Label(root, text="URL")
            e3 = Entry(root, bd=5)
            l4 = Label(root, text="Artist")
            e4 = Entry(root, bd=5)
            l5 = Label(root, text="Type")
            e5 = Entry(root, bd=5)
            add=Button(root,text="Add",height=2,width=12,command=Add)
            l1.pack()
            e1.pack()
            l2.pack()
            e2.pack()
            l3.pack()
            e3.pack()
            l4.pack()
            e4.pack()
            l5.pack()
            e5.pack()
            add.pack()
            root.mainloop()


        def deleteSong():
            root = Tk()
            root.title("Delete Song")
            root.minsize(300, 200)
            root.maxsize(300, 200)

            def delete():
            	tp=int(e1.get())
            	sql="delete from Songs where song_id='%d'"%tp
            	cursor.execute(sql)
            	db.commit()
            	messagebox.showinfo("Success","Song deleted successfully...!!!")

            l1=Label(root,text="Song ID")
            e1=Entry(root,bd=5)

            b1=Button(root,text="Delete Song",height=4,width=12,command=delete)

            l1.pack()
            e1.pack()
            b1.pack()
            root.mainloop()

        def deleteUser():
            root = Tk()
            root.title("Delete User")
            root.minsize(300, 200)
            root.maxsize(300, 200)
            def delete():
                tp=int(e1.get())
                sql="delete from playlist where login_id='%d'"%tp
                cursor.execute(sql)
                db.commit()
                sq = "delete from user where login_id='%d'"%tp
                cursor.execute(sq)
                db.commit()
                messagebox.showinfo("Success","User deleted successfully")
            	
            l1=Label(root,text="Login ID")
            e1=Entry(root,bd=5)

            b1=Button(root,text="Delete User",height=4,width=12,command=delete)

            l1.pack()
            e1.pack()
            b1.pack()
            root.mainloop()

        def dispSongs():
        	root = Tk()
        	root.title("User")
        	sql="select name,url,type,song_id from Songs"
        	cursor.execute(sql)
        	st='NAME'
        	st+='\t\t\t\t'
        	st+='URL'
        	st+='\t\t'
        	st+='TYPE'
        	st+='\t\t'
        	st+='ID'
        	st+='\n\n'
        	for tmp in cursor.fetchall():
        		for one in tmp:
        			st+=str(one)
        			st+='\t\t\t'
        		st+='\n'
        	text=Text(root)
        	text.insert(INSERT,st)
        	text.pack()
           

        addsong=Button(root,text="Add Song",height=3,width=12,command=addSong)
        deletesong=Button(root,text="Delete Song" ,height=3,width=12,command=deleteSong)
        deleteuser=Button(root,text="Delete User",height=3,width=12,command=deleteUser)
        displaysongs=Button(root,text="Display All Songs",height=3,width=12,command=dispSongs)
        addsong.pack()
        deletesong.pack()
        deleteuser.pack()
        displaysongs.pack()
        root.mainloop()



    def userLogin():
        root = Tk()
        root.title("User")
        root.minsize(500, 300)
        root.maxsize(500, 300)

        def addSongToPlaylist():
            root = Tk()
            root.title("Add Song")
            root.minsize(300, 200)
            root.maxsize(300, 200)
            l1 = Label(root, text="Playlist Name")
            e1 = Entry(root, bd=5)
            l2 = Label(root, text="Song ID")
            e2 = Entry(root, bd=5)
            def add():
            	p1=e1.get()
            	p2=int(e2.get())
            	p3= int(LogINID)
            	sql="select type from Songs where song_id='%d'"%p2
            	cursor.execute(sql)
            	t=cursor.fetchone()
            	p4=t[0]
            	sq="insert into playlist values('%s','%d','%d','%s')"%(p1,p2,p3,p4)
            	cursor.execute(sq)
            	db.commit()
            	messagebox.showinfo("Success","Song added successfully")
            b1=Button(root,text="Add",height=5,width=12,command=add)
            l1.pack()
            e1.pack()
            l2.pack()
            e2.pack()
            b1.pack()
            root.mainloop()

        def searchSong():
            root = Tk()
            root.title("Search")
            root.minsize(300, 200)
            root.maxsize(300, 200)
            def ser():
                root=Tk()
                root.title("SONGS")
                t=e1.get()
                sql="select name,url from Songs where type='%s'"%t
                cursor.execute(sql)
                st='NAME'
                st+='\t\t\t'
                st+='URL'
                st+='\n\n'
                for tmp in cursor.fetchall():
                    for one in tmp:
                        st+=str(one)
                        st+='\t\t\t'
                    st+='\n'
                text=Text(root)
                text.insert(INSERT,st)
                text.pack()
                root.mainloop()
            l1=Label(root,text="Enter Type")
            e1=Entry(root,bd=5)
            b1=Button(root,text="Search",height=3,width=12,command=ser)
            l1.pack()
            e1.pack()
            b1.pack()
            root.mainloop()

        def predict():
            root = Tk()
            root.title("Predict")
            root.minsize(500, 300)
            root.maxsize(500, 300)
            global LogINID
            tp=int(LogINID)
            '''
            sql1="select type ,count from (select type ,count(type) as count from \
            playlist where login_id=%d group by type ) as p group by count desc limit 1"%tp
            cursor.execute(sql1)
            obj=cursor.fetchone()
            print(obj[0])
            '''
            sql1="drop table if exists temp"
            cursor.execute(sql1)
            db.commit()
            sql1="drop table if exists temp2"
            cursor.execute(sql1)
            db.commit()

            sql1="create table temp as select type from playlist where login_id=%d"%tp
            cursor.execute(sql1)
            db.commit
            sql1="create table temp2 as select type ,count(type) as ct from temp group by type"
            cursor.execute(sql1)
            db.commit()
            sql1="select max(ct) from temp2"
            cursor.execute(sql1)
            al=cursor.fetchall()
            cout=al[0]
            cut=int(cout[0])
            sql1="select type from temp2 where ct=%d"%cut
            cursor.execute(sql1)
            ob=cursor.fetchall()
            obj=ob[0]

            type=str(obj[0])
            sql="select name,url from Songs where type='%s'"%type
            cursor.execute(sql)
            st='NAME'
            st+='\t\t\t'
            st+='URL'
            st+='\n\n'
            for tmp in cursor.fetchall():
                for one in tmp:
                    st+=one
                    st+='\t\t\t'
                st+='\n'
            text=Text(root)
            text.insert(INSERT,st)
            text.pack()
            root.mainloop()
        
        def dispSongs():
        	root = Tk()
        	root.title("User")
        	sql="select name,url,type,song_id from Songs"
        	cursor.execute(sql)
        	st='NAME'
        	st+='\t\t\t\t'
        	st+='URL'
        	st+='\t\t'
        	st+='TYPE'
        	st+='\t\t'
        	st+='ID'
        	st+='\n\n'
        	for tmp in cursor.fetchall():
        		for one in tmp:
        			st+=str(one)
        			st+='\t\t\t'
        		st+='\n'
        	text=Text(root)
        	text.insert(INSERT,st)
        	text.pack()
           
        addsong=Button(root,text="Add to playlist",height=3,width=12,command=addSongToPlaylist)
        search=Button(root,text="Search",height=3,width=12,command=searchSong)
        predict=Button(root,text="Predict Song ",height=3,width=12,command=predict)
        displaysongs=Button(root,text ="Display All Sogs",height=3,width=12,command=dispSongs)
        addsong.pack()
        search.pack()
        predict.pack()
        displaysongs.pack()

    def adminLoginVerify():
        root = Tk()
        root.title("Verify")
        root.minsize(500, 300)
        root.maxsize(500, 300)

        l1=Label(root,text='Login ID')
        e1=Entry(root,bd=5)
        l2=Label(root,text='Password')
        e2=Entry(root,bd=5)
        def check():
        	loginid=e1.get()
        	password=e2.get()
        	tp=int(loginid)
        	sql="select admin_pass from admin where admin_id='%d'"%tp
        	cursor.execute(sql)
        	st=cursor.fetchone()
        	if password==st[0]:
        		adminLogin()
        	else:
        		messagebox.showinfo("failed","Authentication faliure ....") 
        b1=Button(root,text="Login ",height=3,width=12,command=check)
        l1.pack()
        e1.pack()
        l2.pack()
        e2.pack()
        b1.pack()
        root.mainloop()

    def userLoginVerify():
        root = Tk()
        root.title("Verify")
        root.minsize(500, 300)
        root.maxsize(500, 300)

        l1 = Label(root, text='Login ID')
        e1 = Entry(root, bd=5)
        l2 = Label(root, text='Password')
        e2 = Entry(root, bd=5)
        def check():
        	loginid=e1.get()
        	global LogINID
        	LogINID=loginid
        	password=e2.get()
        	tp=int(loginid)
        	sql="select user_pass from user where login_id='%d'"%tp
        	cursor.execute(sql)
        	st=cursor.fetchone()
        	if password==st[0]:
        		userLogin()
        	else:
        		messagebox.showinfo("failed","Authentication faliure ....") 
        	
        b1=Button(root,text="Login ",height=3,width=12,command=check)
        l1.pack()
        e1.pack()
        l2.pack()
        e2.pack()
        b1.pack()
        root.mainloop()

    admin=Button(root,text="Admin Login",height=5,width=12,command=adminLoginVerify)
    user=Button(root,text="User Login",height=5,width=12,command=userLoginVerify)

    admin.pack()
    user.pack()

def signup():
    root = Tk()
    root.title("Sign Up")
    root.minsize(500, 300)
    root.maxsize(500, 300)

    def insertUserInfo():
        st1=e1.get()
        st2=e2.get()
        sql="insert into user values('%s','%s')"%(st1,st2) 
        cursor.execute(sql)
        db.commit()
        messagebox.showinfo('Successful','Signed up successfully....')


    l1=Label(root,text="Login ID")
    l2=Label(root,text='Password')

    e1=Entry(root,bd=5)
    e2=Entry(root,bd=5)
    signupButton=Button(root,height=3,width=12,text="Sign Up",command=insertUserInfo)
    l1.pack()
    e1.pack()
    l2.pack()
    e2.pack()
    signupButton.pack()
    root.mainloop()



def mainFunction():
    root_main=Tk()
    root_main.title("Menu")
    root_main.minsize(500,300)
    root_main.maxsize(500,300)
    logIn=Button(root_main,text="Log In",height=5,width=12,command=login)
    signUp=Button(root_main,text="Sign Up", height=5, width=12,command=signup)
    logIn.pack()
    signUp.pack()

    root_main.mainloop()

if __name__=='__main__':
    mainFunction()
