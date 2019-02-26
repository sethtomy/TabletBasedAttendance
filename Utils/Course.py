import datetime
import random

class Course():

    def __init__(self, roster, instructor, name, warningThreshold=3):
        self.roster = roster
        self.instructor = instructor
        self.name = name
        self.warningThreshold = warningThreshold
        self.attendance_session_list = []
        self.current_attendance_session = 0

    def to_dict(self):
        dest = {
            'instructor': self.instructor,
            'name': self.name,
            'warningThreshold': self.warningThreshold,
            'roster': self.roster.student_list
        }
        return dest

    def startAttendanceSession(self):
        attendance_session = AttendanceSession(self.name, self.roster)
        # random generation of attendance until connected to front-end
        for student_id in self.roster:
            attendance_session.update_attendance(student_id, bool(random.getrandbits(1)))
        self.attendance_session_list.append(attendance_session)
        self.current_attendance_session += 1

class Roster():

    def __init__(self):
        self.student_list = []

    # keeping a list of student id's
    def add_student(self, student_id):
        self.student_list.append(student_id)


class AttendanceSession():

    def __init__(self, course_id, roster):
        self.dateTime = datetime.datetime.now()
        self.course_id = course_id
        self.attendedClassList = {}
        # initialize to false
        for student_id in roster:
            self.attendedClassList[student_id] = False

    def update_attendance(self, student_id, didAttend):
        self.attendedClassList[student_id] = didAttend

    def to_dict(self):
        dest = {
            'course_id': self.course_id,
            'dateTime': self.dateTime,
            'attendedClassList': list(self.attendedClassList)
        }