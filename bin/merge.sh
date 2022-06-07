#!/bin/bash

git checkout dev
git merge feature/user_profile --no-ff
git push