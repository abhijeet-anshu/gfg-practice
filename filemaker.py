#! /usr/bin/python3

'''
File : filemaker.py
Author : abhijeet baranwal

Description:
The program creates a template file based on the type entered
'''


import sys, os

File_Types = ['.cpp']

def CPPHandle(filename):
	extensionInd         = filename.find('.') + 1
	nameWithoutExtension = filename[:extensionInd]
	txtFileName			 = nameWithoutExtension+'txt'

	with open(filename, 'w') as file:

		file.write('/*\n\n\n')
		file.write('File : %s\n'%filename)
		file.write('Author : %s\n'%os.environ['USER'])
		file.write('\nSource Problem URL:\n')
		file.write('#Add URL here:\n')
		file.write('\n\nTo run the file, run below command:\n')
		file.write('g++ {} && ./a.out <{}\n'.format(filename, txtFileName))
		file.write('\n\n*/\n')
		file.write('\n#include <bits/stdc++.h>\n')
		file.write('using namespace std;\n\n')

	with open(txtFileName, 'a'):
		os.utime(txtFileName, None)

	print(f'Successfully created: {filename}, {txtFileName}')

def createFILE(filename):	
	if os.path.isfile(filename):
		raise StandardError('File with same name exists!') 

	ind = 1 + filename.find('.')
	if ind == 0:
		raise NameError('File name must have a type.\n'
			'Types allowed: ['+ (','.join(File_Types)).upper() + ']')

	execFuncName = filename[ind:].upper() + 'Handle'
	#Execute the function dynamically generated
	#for example CPPHandle
	try:
		getattr(sys.modules[__name__], "%s" % execFuncName)(filename)
	except AttributeError as e:
		raise NotImplementedError(('The %s file type is not implemented'% 
			filename[ind:].upper() + 
			'\nTypes allowed: ['+ (','.join(File_Types)).upper() + ']'))

program_name = sys.argv[0]
arguments = sys.argv[1:]

count_args = len(arguments)
if count_args == 0:
	raise Exception(('Atleast 1 file has to be specified.\n' 
		'Types allowed: ['+ (','.join(File_Types)).upper() + ']'))

for file_ind in range(1, count_args+1):
	filename = sys.argv[file_ind]
	createFILE(filename)










