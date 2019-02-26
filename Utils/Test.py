from Course import *
import config
import Firebase

roster = []
for student in config.roster:
    roster.append(student[0].split('@')[0])

course = Course(roster, 'amussa', 'SOFTWARE ENGINEERING-CTW Section 003 Spring Semest')

Firebase.put_test_att_session(course)