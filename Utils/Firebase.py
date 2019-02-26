from Human import Student
from Course import Roster, Course

import firebase_admin
from firebase_admin import credentials
from firebase_admin import firestore

project_id = "tabletbasedatt-test"

# Use a service account
cred = credentials.Certificate('/Users/sethtomy/Developer/Google/eAttendKeys.json')
firebase_admin.initialize_app(cred)

db = firestore.client()

def put_test_students(data):

    for student in data:
        email = student[0]
        first = student[1]
        last = student[2]
        stu = Student(email, first, last)
        id = email.split('@')[0]
        db.collection('student').document(id).set(stu.to_dict())

def put_test_instructor(instructor):

    id = instructor.email.split('@')[0]
    db.collection('instructor').document(id).set(instructor.to_dict())

def put_test_course(data):

    roster = Roster()
    # using course name for id, for now
    for student in data:
        id = student[0].split('@')[0]
        roster.add_student(id)

    # hard code for now
    course = Course(roster, 'amussa', 'SOFTWARE ENGINEERING-CTW Section 003 Spring Semest')
    id = course.name
    db.collection('course').document(id).set(course.to_dict())

def put_test_att_session(course):

    course.startAttendanceSession()
    attendance_session = course.attendance_session_list[course.current_attendance_session - 1]
    id = course.name + ' : ' + str(attendance_session.dateTime.month) + '-' + str(attendance_session.dateTime.day) + \
         '-' + str(attendance_session.dateTime.year)
    db.collection('attendance').document(id).set(attendance_session.attendedClassList)