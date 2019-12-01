CREATE SCHEMA mdl_communicationtb_template

CREATE TABLE mdl_communication.tb_template
(
	n_id SERIAL NOT NULL PRIMARY KEY,
	s_name VARCHAR(50) NOT NULL,
	s_content TEXT NOT NULL,
	s_params TEXT,
	s_subject TEXT NOT NULL
);


CREATE TABLE mdl_communication.tb_send_email_log
(
	n_id SERIAL NOT NULL PRIMARY KEY,
	s_template_name VARCHAR(50) NOT NULL,
	s_username VARCHAR(150) NOT NULL,
	d_date_send TIMESTAMP NOT NULL,
	s_params_value TEXT NOT NULL
);

-- INSERT INTO mdl_communication.tb_template(s_name,s_content,s_subject,s_params)
-- VALUES ('SignUpSuccess','Dear ${username}, You signed up successfully your account. Thanks for using our sevices','Sign up successful',''),
-- 		('ResetPassword','Dear ${username}, Your password has just been changed to ${password}','Change password successful','password')	


CREATE TABLE mdl_communication.tb_token_user
(
	n_id SERIAL NOT NULL PRIMARY KEY,
	s_username VARCHAR(150) NOT NULL,
	s_token TEXT NOT NULL,
	d_created TIMESTAMP NOT NULL 
);

CREATE TABLE mdl_communication.tb_notification_template
(
	n_id SERIAL NOT NULL PRIMARY KEY,
	s_name VARCHAR(50) NOT NULL,
	s_content TEXT NOT NULL,
	s_params VARCHAR(50) 	
);

CREATE TABLE mdl_communication.tb_send_notication_log
(
	n_id SERIAL NOT NULL PRIMARY KEY,
	s_name VARCHAR(50) NOT NULL,
	s_content TEXT NOT NULL,
	s_params TEXT NOT NULL
);


tb_token_userCREATE TABLE mdl_communication.tb_verify_otp
(
	s_transaction_id VARCHAR(50) NOT NULL,
	s_username VARCHAR(150) NOT NULL,
	s_otp VARCHAR(6) NOT NULL,
	d_created_date TIMESTAMP  NOT NULL,
	n_is_verified INTEGER DEFAULT 0, 
	PRIMARY KEY (s_transaction_id,s_username)
);

CREATE TABLE mdl_communication.tb_setting
(
	n_id SERIAL NOT NULL PRIMARY KEY,
	s_name VARCHAR(50) NOT NULL UNIQUE,
	n_value INTEGER NOT NULL
);

INSERT INTO mdl_communication.tb_setting(s_name,n_value)
VALUES ('otpExpireIn',5)

INSERT INTO mdl_communication.tb_notification_template(s_name,s_content,s_params)
VALUES ('SENDOTP', 
'
{
  "to": ${clientAppId},
  "priority": "high",
  "notification": 
  {
    "title": "OTP Verify",
    "body": "OTP : ${otp}"
  }
}
','transactionId'
)
